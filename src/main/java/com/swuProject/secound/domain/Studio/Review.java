package com.swuProject.secound.domain.Studio;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Studio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Review {

    @Id @GeneratedValue
    @Column(name="review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photo_id")
    private Photo photo;

    // 만족도
    private Long statisfaction;
    // 청결도
    private Long cleanliness;
    // 관리 상태
    private Long management;

    private String review;

    // 다대일 단방향 - 사진
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studio_id")
    private Studio studio;

    public Review(Long id, Long statisfaction, Long cleanliness, Long management, String review) {
        this.id = id;
        this.statisfaction = statisfaction;
        this.cleanliness = cleanliness;
        this.management = management;
        this.review = review;
    }

    public void setMapping(Member member, Photo photo, Studio studio){
        this.member = member;
        this.photo = photo;
        this.studio = studio;
    }

    public Long getMemberId(){return member.getId();}

    public Long getPhotoId(){return photo.getId();}

    public Long getAlbumId(){return photo.getAlbumId();}

    public Long getStudioId(){return photo.getStudioId();}

    public String getUsername() {
        return member.getUsername();
    }
}
