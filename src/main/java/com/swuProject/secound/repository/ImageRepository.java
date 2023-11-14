package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    // 사진 id를 기준으로 해당 사진의 이미지 조회
    List<Image> findByPhotoId(Long photoId);
}
