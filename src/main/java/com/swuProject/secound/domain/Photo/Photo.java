package com.swuProject.secound.domain.Photo;

import com.swuProject.secound.domain.Member.Hashtag;
import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Studio.Studio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Photo {

    @Id @GeneratedValue
    @Column(name = "photo_id")
    private Long id;

    // 다대일 단방향 - 앨범
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    // 일대일 단방향 - 이미지
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="image_id")
    private Image image;

    // 다대일 단방향 - 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 다대일 단방향 - 사진관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studio_id")
    private Studio studio;

//    // 일대다 양방향 - 해시태그
//    @OneToMany(mappedBy = "photo")
//    private List<Hashtag> hashtagList = new ArrayList<>();

    private LocalDate filmingDate;
    private String content;
    private Boolean anonymous;
    private Integer numberOfPeople;



    public Photo(Long id, LocalDate filmingDateType, String content, Boolean anonymous, Integer numberOfPeople) {
        this.id = id;
        this.filmingDate = filmingDateType;
        this.content = content;
        this.anonymous = anonymous;
        this.numberOfPeople = numberOfPeople;
    }


    // 연관관계 매핑 메소드
    public void setMapping(Member member, Album album, Studio studio, Image image) {
        this.member = member;
        this.album = album;
        this.studio = studio;
        this.image = image;
    }

    // 매핑된 앨범 아이디 반환
    public Long getAlbumId() {
        return album.getId();
    }

    // 매핑된 유저의 이름 반환
    public String getUsername() {
        return member.getUsername();
    }

    // 매핑된 이미지 아이디 반환
    public Long getImageId() {
        return image.getId();
    }

    // 매핑된 스튜디오 아이디 반환
    public Long getStudioId() {
        return studio.getId();
    }


}

