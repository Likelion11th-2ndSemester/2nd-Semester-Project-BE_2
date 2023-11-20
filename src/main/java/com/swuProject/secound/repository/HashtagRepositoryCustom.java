package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Member.Hashtag;
import com.swuProject.secound.domain.Photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepositoryCustom {
    List<Photo> findPhotosByMemberUsername(String username);
}
