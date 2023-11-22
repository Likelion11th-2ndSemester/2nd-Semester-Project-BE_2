package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

    // 촬영 날짜로 사진 조회
    List<Photo> findByFilmingDate(LocalDate filmingDate);

    // 공개된 사진 조회
    List<Photo> findByAnonymousIsTrue();
}
