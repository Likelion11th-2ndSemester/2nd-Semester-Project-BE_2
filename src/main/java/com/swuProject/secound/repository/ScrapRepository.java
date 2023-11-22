package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Photo.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

    Scrap findByMemberAndPhoto(Member member, Photo photo);

}
