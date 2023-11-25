package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Studio.Review;
import com.swuProject.secound.domain.Studio.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByStudio(Studio studio);

    // 다른 필요한 메서드들도 추가 가능

    // 스튜디오와 매핑된 리뷰의 만족도 평균 계산
    @Query("SELECT AVG(r.statisfaction) FROM Review r WHERE r.studio = :studio")
    Double findAverageSatisfactionByStudio(@Param("studio") Studio studio);

    // 스튜디오와 매핑된 리뷰의 청결도 평균 계산
    @Query("SELECT AVG(r.cleanliness) FROM Review r WHERE r.studio = :studio")
    Double findAverageCleanlinessByStudio(@Param("studio") Studio studio);

    // 스튜디오와 매핑된 리뷰의 관리도 평균 계산
    @Query("SELECT AVG(r.management) FROM Review r WHERE r.studio = :studio")
    Double findAverageManagementByStudio(@Param("studio") Studio studio);
}
