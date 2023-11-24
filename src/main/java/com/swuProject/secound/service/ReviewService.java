package com.swuProject.secound.service;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Album;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Review;
import com.swuProject.secound.domain.Studio.Studio;
import com.swuProject.secound.dto.request.ReviewFromDto;
import com.swuProject.secound.repository.MemberRepository;
import com.swuProject.secound.repository.PhotoRepository;
import com.swuProject.secound.repository.ReviewRepository;
import com.swuProject.secound.repository.StudioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final StudioRepository studioRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PhotoRepository photoRepository;

    public Long createReview(ReviewFromDto reviewFromDto, String email, Long id) throws Exception {
        try {
            Review review = reviewFromDto.createReview();
            Member member = memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

            Long photo_id = id;
            Photo photo = photoRepository.findById(photo_id).orElseThrow(EntityNotFoundException::new);

            Long studio_id = reviewFromDto.getStudio_id();
            Studio studio = studioRepository.findById(studio_id).orElseThrow(EntityNotFoundException::new);

            review.setMapping(member,photo,studio);
            reviewRepository.save(review);

            return review.getId();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
