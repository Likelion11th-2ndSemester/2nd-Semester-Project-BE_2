package com.swuProject.secound.service;

import com.swuProject.secound.domain.Image;
import com.swuProject.secound.domain.Photo;
import com.swuProject.secound.dto.request.ImageFormDto;
import com.swuProject.secound.dto.request.PhotoFormDto;
import com.swuProject.secound.repository.ImageRepository;
import com.swuProject.secound.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final ImageService imageService;

    // 사진 생성
    public Long createPhoto(PhotoFormDto photoFormDto, MultipartFile imgFile) throws Exception {

        // 사진 등록
        Photo photo = photoFormDto.createPhoto(); // DTO -> 엔티티 변환
        photoRepository.save(photo);

        // 이미지 등록
        Image image = new Image();
        image.setPhoto(photo);
        imageService.saveImage(image, imgFile);

        return photo.getId();
    }

    // 사진 수정
//    public Long updatePhoto(Long id, PhotoFormDto photoFormDto) throws Exception {
//
//        // 게시글 찾기
//        Photo findPhoto = photoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//
//
//
//    }

}
