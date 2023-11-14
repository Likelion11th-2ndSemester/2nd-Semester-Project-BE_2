package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Image;
import com.swuProject.secound.domain.Photo;
import com.swuProject.secound.dto.request.ImageFormDto;
import com.swuProject.secound.dto.request.PhotoFormDto;
import com.swuProject.secound.dto.response.PhotoReturnDto;
import com.swuProject.secound.repository.PhotoRepository;
import com.swuProject.secound.service.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PhotoController {

    private PhotoService photoService;
    private PhotoRepository photoRepository;

    // 사진 생성
    @ResponseBody
    @PostMapping("/user/photo/new")
    public ResponseEntity createPhoto(@Valid @RequestBody PhotoFormDto photoFormDto,
                                      @RequestParam(name="imgFile") MultipartFile imgFile,
                                      @RequestParam(name="albumId", required=false, defaultValue = "-1") Long albumId,
                                      @RequestParam(name="studioName") String studioName,
                                      Principal principal) {

        String email = principal.getName();

        try {
            Long id = photoService.createPhoto(photoFormDto, imgFile, albumId, studioName, email);
            Photo photo = photoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            PhotoReturnDto photoReturnDto = PhotoReturnDto.PhotoMapper(photo);

            return ResponseEntity.ok(photoReturnDto);

        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
