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

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final PhotoRepository photoRepository;

    private final StudioRepository studioRepository;

    public Long createReview(ReviewFromDto reviewFromDto, String email, Long photoId, Long studioId) {
        try {
            Review review = reviewFromDto.toEntity();
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("Member not found with email: " + email));

            Studio studio = studioRepository.findById(studioId)
                    .orElseThrow(() -> new EntityNotFoundException("스튜디오 없음" + studioId));


            Photo photo = photoRepository.findById(photoId)
                    .orElseThrow(() -> new EntityNotFoundException("Photo not found with id: " + photoId));

            review.setMapping(member, photo, studio);
            reviewRepository.save(review);

            return review.getId();
        } catch (Exception e) {
            // Handle exceptions as needed
            return null;
        }
    }
}
