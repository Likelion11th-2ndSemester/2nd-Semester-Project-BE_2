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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // 일대다 양방향 - 스크랩
    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    private List<Scrap> scrapList = new ArrayList<>();

    // 다대일 단방향 - 사진관
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studio_id")
    private Studio studio;

    // 일대다 양방향 - 해시태그
    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    private List<Hashtag> hashtagList = new ArrayList<>();

    // 일대일 단방향 - 이미지
    @OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="image_id")
    private Image image;

    private LocalDate filmingDate;
    private String content;
    private Boolean anonymous;
    private Integer numberOfPeople;


    // DTO -> 엔티티 (사진 생성 시)
    public Photo(Long id,LocalDate filmingDateType, String content, Boolean anonymous, Integer numberOfPeople) {
        this.id = id;
        this.filmingDate = filmingDateType;
        this.content = content;
        this.anonymous = anonymous;
        this.numberOfPeople = numberOfPeople;
    }


    // 연관관계 매핑 메소드 - 사진 생성 시
    public void setNewMapping(Member member, Album album, Studio studio) {
        this.member = member;
        this.album = album;
        this.studio = studio;
        //this.image = image;

    }


    // 연관관계 매핑 메소드 - 사진 수정 시
    public void setUpdateMapping(Album album, Studio studio) {
        this.album = album;
        this.studio = studio;
    }

    // 태그된 유저 아이디 배열 반환
    public List<Long> getTaggedUserList() {
        List<Hashtag> targetList = this.hashtagList;
        List<Long> taggedUserList = new ArrayList<>();

        for (Hashtag hashtag : targetList) {
            Long member_id = hashtag.getMember().getId();
            taggedUserList.add(member_id);
        }

        return taggedUserList;

    }

    // 앨범 수정 (부분 수정도 가능)
    public void updatePhoto(Photo photo) {
        if (photo.filmingDate != null)
            this.filmingDate = photo.filmingDate;
        if (photo.content != null)
            this.content = photo.content;
        if (photo.anonymous != null)
            this.anonymous = photo.anonymous;
    }

    // 매핑된 앨범 아이디 반환
    public Long getAlbumId() {
        return album.getId();
    }

    // 매핑된 유저의 이름 반환
    public String getUsername() {
        return member.getUsername();
    }

    // 매핑된 스튜디오 아이디 반환
    public Long getStudioId() {
        return studio.getId();
    }


}

