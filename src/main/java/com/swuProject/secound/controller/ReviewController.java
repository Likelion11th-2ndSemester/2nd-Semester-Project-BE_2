package com.swuProject.secound.controller;


import com.swuProject.secound.domain.Studio.Review;
import com.swuProject.secound.dto.request.ReviewFromDto;
import com.swuProject.secound.dto.response.ReviewReturnDto;
import com.swuProject.secound.repository.ReviewRepository;
import com.swuProject.secound.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    //사진별 리뷰
    @PostMapping("/user/photo/review/{photoId}/{studioId}")
    public ResponseEntity<?> createReview(@PathVariable Long photoId,
                                          @PathVariable Long studioId,
                                          @Valid @RequestBody ReviewFromDto reviewFromDto,
                                          Principal principal) {
        try {
            String email = principal.getName();
            Long reviewId = reviewService.createReview(reviewFromDto, email, photoId, studioId);

            if (reviewId != null) {
                return ResponseEntity.ok("Review created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Photo not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create review");
        }
    }


}

