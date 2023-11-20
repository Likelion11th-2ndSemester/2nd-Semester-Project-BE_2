package com.swuProject.secound.domain.Photo;

import com.swuProject.secound.domain.Member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Scrap { // 공개된 사진만 스크랩 가능

    @Id @GeneratedValue
    @Column(name = "scrap_id")
    private Long id;

    // 다대일 단방향 - 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 다대일 단방향 - 사진
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photo_id")
    private Photo photo;

    // 특정 유저 스크랩 여부 확인 메소드 (파라미터: 유저, 사진) - 타입 boolean default=False
}
