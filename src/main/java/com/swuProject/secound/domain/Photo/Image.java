package com.swuProject.secound.domain.Photo;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Image {

    @Id
    @Column(name="img_id")
    @GeneratedValue
    private Long id;

    private String imgName;
    private String originalImgName;
    private String imgPath;
    private String repImg;

    public void updateImg(String originalImgName, String imgName, String imgPath) {
        this.originalImgName = originalImgName;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }
}
