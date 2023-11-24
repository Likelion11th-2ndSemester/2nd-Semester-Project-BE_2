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

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    //리뷰 등록
    @PostMapping("/user/photos/reivew/{id}")
    public ResponseEntity createReview(@PathVariable Long photo_id,
                                       @Valid @RequestBody ReviewFromDto reviewFromDto,
                                       Principal principal){
        try {
            String email = principal.getName();

            Long id = reviewService.createReview(reviewFromDto, email, photo_id);
            Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            ReviewReturnDto reviewReturnDto =ReviewReturnDto.ReviewMapper(review);

            return ResponseEntity.ok(reviewReturnDto);

        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
