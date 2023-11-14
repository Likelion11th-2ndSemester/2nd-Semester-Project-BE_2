package com.swuProject.secound.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Studio {

    @Id @GeneratedValue
    @Column(name="studio_id")
    private Long id;


    // 만족도
    private Long statisfaction;
    // 청결도
    private Long cleanliness;
    // 관리 상태
    private Long management;

    private String review;
    private String phoneNumber;
    private String operatingTime;

    // api 사용
    private String address;
    private String studioName;

}
