package com.swuProject.secound.domain.Studio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Studio {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="studio_id")
    private Long id;


    // 만족도 평균
    private Long statisfaction;
    // 청결도 평균
    private Long cleanliness;
    // 관리 상태 평균
    private Long management;

//    // 총합 필드 추가
//    private Long totalSatisfaction;
//    private Long totalCleanliness;
//    private Long totalManagement;

    // 일대다 양방향 - 리뷰
    @OneToMany(mappedBy = "studio")
    private List<Review> reviewList = new ArrayList<>();

    private String phoneNumber;
    private String operatingTime;

    // api 사용
    private String address;
    private String studioName;

}
