package com.swuProject.secound.entity;

import com.swuProject.secound.security.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Photo {

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    // 다대일 단방향 - 앨범
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    // 다대일 단방향 - 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 다대일 단방향 - 사진관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studio_id")
    private Studio studio;

    private LocalDate filmingDate;
    private String content;
    private Boolean anonymous;
    private Integer numberOfPeople;

}

