package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    Studio findByStudioName(String studioName);
}
