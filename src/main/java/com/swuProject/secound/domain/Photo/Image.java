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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName;
    private String originalImgName;
    private String imgPath;

    public void updateImg(String originalImgName, String imgName, String imgPath) {
        this.originalImgName = originalImgName;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }
}
