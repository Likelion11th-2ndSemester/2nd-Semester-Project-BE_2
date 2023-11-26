package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Review;
import com.swuProject.secound.domain.Studio.Studio;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StudioReturnDto {

    private Long id;


    // 만족도 평균
    private Long statisfaction;
    // 청결도 평균
    private Long cleanliness;
    // 관리 상태 평균
    private Long management;

//    // 일대다 양방향 - 리뷰
//    private List<Review> reviewList = new ArrayList<>();

    private String phoneNumber;
    private String operatingTime;

    // api 사용
    private String address;
    private String studioName;


    public static StudioReturnDto StudioMapper(Studio studio) {

        StudioReturnDto studioReturnDto = new StudioReturnDto();

        studioReturnDto.setId(studio.getId());
        studioReturnDto.setCleanliness(studio.getCleanliness());
        studioReturnDto.setManagement(studio.getManagement());
        studioReturnDto.setStatisfaction(studio.getStatisfaction());
        studioReturnDto.setAddress(studio.getAddress());
        studioReturnDto.setOperatingTime(studio.getOperatingTime());
        studioReturnDto.setPhoneNumber(studio.getPhoneNumber());
        studioReturnDto.setStudioName(studio.getStudioName());

        return studioReturnDto;
    }
}
