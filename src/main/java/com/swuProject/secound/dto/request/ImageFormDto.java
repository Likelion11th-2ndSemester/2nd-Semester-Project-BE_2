package com.swuProject.secound.dto.request;

import com.swuProject.secound.domain.Photo.Image;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ImageFormDto {

    private Long id;
    private String imgName;
    private String originalImgName;
    private String imgPath;

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO -> 엔티티 변환
    public static ImageFormDto ImageMapper(Image image) {

        return modelMapper.map(image, ImageFormDto.class);
    }
}
