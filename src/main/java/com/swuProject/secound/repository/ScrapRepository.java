package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Photo.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    // 특정 유저가 특정 사진의 스크랩 여부를 알기 위해
    Scrap findByMemberAndPhoto(Member member, Photo photo);

    List<Scrap> findByMember(Member member);
}
