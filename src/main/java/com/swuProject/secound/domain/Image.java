package com.swuProject.secound.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Image {
    @Id
    @Column(name="img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName;
    private String originalImgName;
    private String imgPath;
    private String repImg;

    // 일대일 단방향 매핑 - 사진
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="photo_id")
    private Photo photo;

    public void updateImage(String originalImgName, String imgName, String imgPath) {
        this.originalImgName = originalImgName;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }
}
