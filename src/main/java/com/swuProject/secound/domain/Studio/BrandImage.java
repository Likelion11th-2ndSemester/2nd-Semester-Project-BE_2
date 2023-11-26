package com.swuProject.secound.domain.Studio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="brand_image_id")
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
