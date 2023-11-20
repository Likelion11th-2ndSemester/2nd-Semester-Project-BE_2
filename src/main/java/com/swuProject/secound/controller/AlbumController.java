package com.swuProject.secound.controller;

import Album;
import com.swuProject.secound.domain.Photo.Album;
import com.swuProject.secound.dto.request.AlbumFormDto;
import com.swuProject.secound.dto.response.AlbumAllReturnDto;
import com.swuProject.secound.dto.response.AlbumReturnDto;
import com.swuProject.secound.repository.AlbumRepository;
import com.swuProject.secound.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@RestController
public class AlbumController {

    private AlbumService albumService;
    private AlbumRepository albumRepository;

    // 앨범 생성
    @ResponseBody
    @PostMapping("/user/albums")
    public ResponseEntity createAlbum(@RequestBody AlbumFormDto albumFormDto) {
        try {
            Long id = albumService.createAlbum(albumFormDto); // 앨범 생성
            Album album = albumRepository.findById(id).orElseThrow(EntityNotFoundException::new); // 앨범 엔티티 조회

            AlbumAllReturnDto albumAllReturnDto = AlbumAllReturnDto.AlbumMapper(album); // DTO로 변환
            return ResponseEntity.ok(albumAllReturnDto); // 성공 시 dto 반환

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 앨범 수정
    @PutMapping("/user/albums/{album_id}")
    public ResponseEntity updateAlbum(@PathVariable Long album_id,
                                                         @RequestBody AlbumFormDto albumFormDto) {
        try {
            Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new); // 앨범 엔티티 조회
            albumService.updateAlbum(album_id, albumFormDto);
            AlbumAllReturnDto albumAllReturnDto = AlbumAllReturnDto.AlbumMapper(album);
            return ResponseEntity.ok(albumAllReturnDto);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 앨범 삭제
    @DeleteMapping("/user/albums/{album_id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable Long album_id) {
        try {
            albumService.deleteAlbum(album_id);
            return ResponseEntity.ok("앨범이 삭제되었습니다.");

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("앨범을 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // 앨범 전체 조회
    @GetMapping("/user/albums")
    public ResponseEntity albumList(@RequestParam(value = "username", required = false)
                                                                 String username) {
        // 검색어가 있는 경우

        if (username != null) {
            try {
                List<AlbumAllReturnDto> albumAllReturnDtos = albumService.getAlbumListWithHashtag(username);
                return ResponseEntity.ok(albumAllReturnDtos);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else { // 앨범 전체 조회
            try {
                List<AlbumAllReturnDto> albumAllReturnDtos = albumService.getAlbumList();
                return ResponseEntity.ok(albumAllReturnDtos);

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
    }

    // 앨범 상세 조회
    @GetMapping("/user/albums/{album_id}")
    public ResponseEntity albumDetail(@PathVariable Long album_id) {
        try {
            AlbumReturnDto albumReturnDto = albumService.getAlbumDetail(album_id);
            return ResponseEntity.ok(albumReturnDto);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("앨범을 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
