package com.swuProject.secound.controller;

import com.swuProject.secound.repository.PhotoRepository;
import com.swuProject.secound.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PhotoController {

    private PhotoService photoService;
    private PhotoRepository photoRepository;

//    // 사진 생성
//    @ResponseBody
//    @PostMapping("/user/photo/new")
//    public ResponseEntity createPhoto(@Valid @RequestBody PhotoFormDto photoFormDto,
//
//                                      Principal principal) {
//
//        String email = principal.getName();
//
//        try {
//            Long id = photoService.createPhoto(photoFormDto, imgFile, albumId, studioName, email);
//            Photo photo = photoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//            PhotoReturnDto photoReturnDto = PhotoReturnDto.PhotoMapper(photo);
//
//            return ResponseEntity.ok(photoReturnDto);
//
//        } catch (Exception e) {
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
