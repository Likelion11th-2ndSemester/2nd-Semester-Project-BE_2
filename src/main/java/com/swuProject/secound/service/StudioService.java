package com.swuProject.secound.service;


import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Review;
import com.swuProject.secound.domain.Studio.Studio;
import com.swuProject.secound.dto.response.PhotoReturnDto;
import com.swuProject.secound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;
    private final ReviewRepository reviewRepository;
    private final PhotoRepository photoRepository;

    // 스튜디오 ID로 스튜디오 조회
    public Studio getStudioById(Long id) {
        return studioRepository.findById(id).orElse(null);
    }

    // 스튜디오와 매핑된 리뷰의 만족도 평균 계산
    public Double calculateAverageSatisfactionByStudio(Studio studio) {
        List<Review> reviews = reviewRepository.findByStudio(studio);
        if (reviews.isEmpty()) {
            return 0.0; // 리뷰가 없을 경우 0.0 반환 또는 원하는 기본값 설정
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getStatisfaction();
        }

        return sum / reviews.size();
    }

    // 스튜디오와 매핑된 리뷰의 청결도 평균 계산
    public Double calculateAverageCleanlinessByStudio(Studio studio) {
        List<Review> reviews = reviewRepository.findByStudio(studio);
        if (reviews.isEmpty()) {
            return 0.0; // 리뷰가 없을 경우 0.0 반환 또는 원하는 기본값 설정
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getCleanliness();
        }

        return sum / reviews.size();
    }

    // 스튜디오와 매핑된 리뷰의 관리도 평균 계산
    public Double calculateAverageManagementByStudio(Studio studio) {
        List<Review> reviews = reviewRepository.findByStudio(studio);
        if (reviews.isEmpty()) {
            return 0.0; // 리뷰가 없을 경우 0.0 반환 또는 원하는 기본값 설정
        }

        double sum = 0;
        for (Review review : reviews) {
            sum += review.getManagement();
        }

        return sum / reviews.size();
    }

    // 스튜디오와 매핑된 리뷰의 리뷰 목록 조회
    public List<String> getReviewsByStudioId(Long studioId) {
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new EntityNotFoundException("Studio not found with id: " + studioId));

        List<String> reviewList = new ArrayList<>();
        for (Review review : studio.getReviewList()) {
            reviewList.add(review.getReview());
        }

        return reviewList;
    }

    public List<Photo> getPhotoList(Long studioId) {
        // 스튜디오 ID로 스튜디오 엔티티를 조회
        Studio studio = studioRepository.findById(studioId)
                .orElseThrow(() -> new EntityNotFoundException("Studio not found with id: " + studioId));

        // 조회한 스튜디오 엔티티를 사용하여 사진 조회
        return photoRepository.findByStudio(studio);
    }

}
