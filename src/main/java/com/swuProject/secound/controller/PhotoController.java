package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.dto.request.PhotoFormDto;
import com.swuProject.secound.dto.response.PhotoReturnDto;
import com.swuProject.secound.repository.PhotoRepository;
import com.swuProject.secound.service.ImageService;
import com.swuProject.secound.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private PhotoRepository photoRepository;


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
}
