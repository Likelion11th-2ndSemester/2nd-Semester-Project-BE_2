package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Member.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findByPhotoId(Long photoId);
}
