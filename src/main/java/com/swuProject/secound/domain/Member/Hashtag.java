package com.swuProject.secound.domain.Member;

import com.swuProject.secound.domain.Photo.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Hashtag { // 친구 태그

    @Id @GeneratedValue
    @Column(name="hashtag_id")
    private Long id;

    // 다대일 단방향 - 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 다대일 단방향 - 사진
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photo_id")
    private Photo photo;

    public Hashtag(Member member, Photo photo) {
        this.member = member;
        this.photo = photo;
    }

    // 유저 네임 반환 메소드 - 사진 상세 조회에서 태그된 유저 이름 출력 위해

}
