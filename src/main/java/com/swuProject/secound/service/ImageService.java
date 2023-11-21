package com.swuProject.secound.service;

import com.swuProject.secound.domain.Photo.Image;
import com.swuProject.secound.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ImageService {

    @Value("${photoImageLocation}")
    private String photoImageLocation;

    private final ImageRepository imageRepository;
    private final FileService fileService;

    // 상품 이미지 저장
    public void saveImg(Image image, MultipartFile imageFile) throws Exception {
        String originalImgName = imageFile.getOriginalFilename();
        String imgName = "";
        String imgPath = "";

        // 파일 업로드
        if (!StringUtils.isEmpty(originalImgName)) {
            imgName = fileService.uploadFile(photoImageLocation, originalImgName, imageFile.getBytes());
            imgPath = "/images/photo/" + imgName;
        }

        // 상품 이미지 정보 저장
        image.updateImg(originalImgName, imgName, imgPath);
        imageRepository.save(image);
    }

    // 아이템 이미지 수정
    public void updateItemImg(Long imageId, MultipartFile imgFile) throws Exception {
        if (!imgFile.isEmpty()) {
            // 상품 id를 통해 상품 이미지 조회한 후 itemImg 변수에 저장
            Image savedImg = imageRepository.findById(imageId).orElseThrow(EntityNotFoundException::new);

            // 기존 등록된 상품 이미지가 있는 경우 삭제
            if (!StringUtils.isEmpty(savedImg.getOriginalImgName())) {
                fileService.deleteFile(savedImg.getImgPath());
            }

            // 수정하고자 하는 상품 이미지 정보 세팅(업데이트)
            String originalImgName = savedImg.getOriginalImgName();
            String imgName = fileService.uploadFile(photoImageLocation, originalImgName, imgFile.getBytes());
            String imgPath = "/images/photo/" + imgName;

            savedImg.updateImg(originalImgName, imgName, imgPath);
        }
    }

}
