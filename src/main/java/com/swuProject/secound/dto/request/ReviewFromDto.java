package com.swuProject.secound.dto.request;


import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Review;
import com.swuProject.secound.domain.Studio.Studio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
public class ReviewFromDto {


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


    public Review createReview(){
        return new Review(id,statisfaction,cleanliness,management,review);
    }

}
