package com.swuProject.secound.domain.Photo;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.repository.ScrapRepository;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Scrap(Member member, Photo photo) {
        this.member = member;
        this.photo = photo;
    }
}
