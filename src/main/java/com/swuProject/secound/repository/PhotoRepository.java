package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
