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

    private Long satisfaction;
    private Long cleanliness;
    private Long management;
    private String review;

    public Review toEntity() {
        return new Review(null, satisfaction, cleanliness, management, review);
    }
}
