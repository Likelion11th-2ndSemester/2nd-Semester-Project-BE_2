package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Photo.Image;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.dto.request.ImageFormDto;
import com.swuProject.secound.dto.request.PhotoFormDto;
import com.swuProject.secound.dto.request.PhotoUpdateDto;
import com.swuProject.secound.dto.response.AlbumReturnDto;
import com.swuProject.secound.dto.response.PhotoCalendarDto;
import com.swuProject.secound.dto.response.PhotoPublicDto;
import com.swuProject.secound.dto.response.PhotoReturnDto;
import com.swuProject.secound.repository.ImageRepository;
import com.swuProject.secound.repository.PhotoRepository;
import com.swuProject.secound.service.ImageService;
import com.swuProject.secound.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoRepository photoRepository;
    private final ImageRepository imageRepository;

    // 사진 등록
    @PostMapping("/user/photos")
    public ResponseEntity createPhoto(@RequestPart("photoFormDto") PhotoFormDto photoFormDto,
                                      @RequestPart(name="imgFile") MultipartFile imgFile,
                                      Principal principal) {
        // 사용자 조회
        String email = principal.getName();

        try {
            Long id = photoService.createPhoto(photoFormDto, email, imgFile);
            Photo photo = photoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            PhotoReturnDto photoReturnDto = PhotoReturnDto.PhotoMapper(photo);

            return ResponseEntity.ok(photoReturnDto);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 사진 수정
    @PutMapping ("/user/photos/{photo_id}")
    public ResponseEntity updatePhoto(@PathVariable Long photo_id,
                                      @RequestPart("photoUpdateDto") PhotoUpdateDto photoUpdateDto,
                                      @RequestPart(name="imgFile") MultipartFile imgFile) {
        try {
            Long id = photoService.updatePhoto(photo_id, photoUpdateDto, imgFile);
            Photo photo = photoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            PhotoReturnDto photoReturnDto = PhotoReturnDto.PhotoMapper(photo);

            return ResponseEntity.ok(photoReturnDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("앨범을 찾을 수 없습니다.");

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 사진 삭제
    @DeleteMapping("/user/photos/{photo_id}")
    public ResponseEntity<String> deletePhoto(@PathVariable Long photo_id) {
        try {
            photoService.deletePhoto(photo_id);
            return ResponseEntity.ok("사진이 삭제되었습니다.");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사진을 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 사진 상세 조회
    @GetMapping("/user/photos/{photo_id}")
    public ResponseEntity photoDetail(@PathVariable Long photo_id) {
        try {
            // 사진 조회
            PhotoReturnDto photoReturnDto = photoService.getPhotoDetail(photo_id);

            // 이미지 조회 후 DTO로 변환 후 사진 DTO에 넣어줌
            Photo photo = photoRepository.findById(photo_id).orElseThrow(EntityNotFoundException::new);
            Image image = photo.getImage();
            ImageFormDto imageFormDto = ImageFormDto.ImageMapper(image);
            photoReturnDto.setImageFormDto(imageFormDto);

            return ResponseEntity.ok(photoReturnDto);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 마이페이지 - 달력 에서 촬영 날짜별 등록된 사진 전체 조회
    @GetMapping("/user/photos/calendar")
    public ResponseEntity getPhotoCalendar(@RequestParam(name = "year") String year,
                                           @RequestParam(name = "month") String month,
                                           @RequestParam(name = "date") String date) {
        try {
            String filmingDate = year + "-" + month + "-" + date;
            System.out.println(filmingDate);
            PhotoCalendarDto photoCalendarDto = photoService.getPhotoCalendar(filmingDate);
            return ResponseEntity.ok(photoCalendarDto);
        } catch (EntityNotFoundException e) { // exception custom 필요
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 날짜에 촬영된 사진이 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 공개된 사진 전체 조회 - 포즈 추천 페이지
    @GetMapping("/user/photos/public")
    public ResponseEntity getPhotoPublic(Principal principal) {
        try {
            String email = principal.getName();
            List<PhotoPublicDto> photoPublicDtoList = photoService.getPhotoPublic(email);

            return ResponseEntity.ok(photoPublicDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 스크랩 / 스크랩 취소
    @GetMapping("/user/{photo_id}/scrap")
    public ResponseEntity<String> scrapStatus(@PathVariable Long photo_id, Principal principal) {
        try {
            String email = principal.getName();
            Photo photo = photoRepository.findById(photo_id).orElseThrow(EntityNotFoundException::new);
            photoService.scrapStatus(email, photo);

            return ResponseEntity.ok("스크랩 상태 변경 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());}
    }

    // 태그된 친구명을 가진 사진 전체 조회
    @GetMapping("/user/{album_id}/photos")
    public ResponseEntity getTaggedPhotoList(@PathVariable Long album_id,
                                             @RequestParam(value="username") String username) {
        try {
            AlbumReturnDto albumReturnDto = photoService.getTaggedPhotoList(album_id, username);
            return ResponseEntity.ok(albumReturnDto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 포즈 추천 페이지 - 인원 수 필터링
    @GetMapping("/user/public/photos")
    public ResponseEntity photoPublicFiltered(@RequestParam(value = "numberOfPeople") Integer numberOfPeople,
                                               Principal principal) {
        try {
            String email = principal.getName();
            List<PhotoPublicDto> photoPublicDtoList = photoService.getPhotoPublicFiltered(email, numberOfPeople);

            return ResponseEntity.ok(photoPublicDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 스크랩한 사진 전체 조회
    @GetMapping("/user/photos/scrap")
    public ResponseEntity photoScrapped(Principal principal) {
        try {
            String email = principal.getName();
            List<PhotoPublicDto> photoPublicDtoList = photoService.getPhotoScrapped(email);
            return ResponseEntity.ok(photoPublicDtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
