package com.swuProject.secound.service;

import com.swuProject.secound.domain.Image;
import com.swuProject.secound.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

    private final FileService fileService;
    private final ImageRepository imageRepository;

    private String imageLocation;

    // 사진 이미지 저장
    public void saveImage(Image image, MultipartFile imgFile) throws Exception {
        String originalImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String imgPath = "";

        // 파일 업로드
        if (!StringUtils.isEmpty(originalImgName)) {
            imgName = fileService.uploadFile(imageLocation, originalImgName, imgFile.getBytes());
            imgPath = "/images/photo/" + imgName;
        }

        // 사진 이미지 정보 저장
        image.updateImage(originalImgName, imgName, imgPath);
        imageRepository.save(image);
    }

    // 사진 이미지 수정
    public void updateImage(Long imgId, MultipartFile imgFile) throws Exception {
        if (!imgFile.isEmpty()) {
            // 사진 id를 통해 이미지 조회한 후 savedImage 변수에 저장
            Image savedImage = imageRepository.findById(imgId).orElseThrow(EntityNotFoundException::new);

            // 기존 등록된 사진 이미지가 있는 경우 삭제
            if (!StringUtils.isEmpty(savedImage.getOriginalImgName())) {
                fileService.deleteFile(savedImage.getImgPath());
            }

            // 수정하고자 하는 이미지 정보 세팅 (업데이트)
            String originalImgName = savedImage.getOriginalImgName();
            String imgName = fileService.uploadFile(imageLocation, originalImgName, imgFile.getBytes());
            String imgPath = "/images/photo/" + imgName;

            savedImage.updateImage(originalImgName, imgName, imgPath);
        }
    }
}
