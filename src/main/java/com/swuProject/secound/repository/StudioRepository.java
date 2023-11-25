package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Studio.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {

    @Query("SELECT s, AVG(r.statisfaction) as avgSatisfaction, AVG(r.cleanliness) as avgCleanliness, AVG(r.management) as avgManagement " +
            "FROM Studio s LEFT JOIN s.reviewList r WHERE s.id = :studioId GROUP BY s")
    Optional<StudioWithAverages> findStudioWithAveragesById(@Param("studioId") Long studioId);

    interface StudioWithAverages {
        Studio getStudio();
        Double getAvgSatisfaction();
        Double getAvgCleanliness();
        Double getAvgManagement();
    }
}

