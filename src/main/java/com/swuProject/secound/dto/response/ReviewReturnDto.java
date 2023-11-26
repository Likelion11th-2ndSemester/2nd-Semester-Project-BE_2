package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Studio.Review;
import com.swuProject.secound.domain.Studio.Studio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewReturnDto {

    private Long id;

    private Long photo_id;

    private Long studio_id;

    // 만족도
    private Long statisfaction;
    // 청결도
    private Long cleanliness;
    // 관리 상태
    private Long management;

    private String review;

    private String username;


    public static ReviewReturnDto ReviewMapper(Review review){
        ReviewReturnDto reviewReturnDto = new ReviewReturnDto();

        reviewReturnDto.setId(review.getId());
        reviewReturnDto.setManagement(review.getManagement());
        reviewReturnDto.setCleanliness(review.getCleanliness());
        reviewReturnDto.setStatisfaction(review.getStatisfaction());
        reviewReturnDto.setPhoto_id(review.getPhotoId());
        reviewReturnDto.setStudio_id(review.getStudioId());
        reviewReturnDto.setUsername(review.getUsername());

        return reviewReturnDto;
    }
}
