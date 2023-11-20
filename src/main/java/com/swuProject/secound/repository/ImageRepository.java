package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Photo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    // 사진 id를 기준으로 해당 사진의 이미지 조회
   Image findByPhotoId(Long photoId);
}
