package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Album;
import com.swuProject.secound.domain.Photo.Photo;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;

@Getter @Setter
public class PhotoDto {

    private Long photo_id;
    private Long image_id;


    // 엔티티 -> DTO 변환
    public static PhotoDto photoDtoMapper(Photo photo) {
        PhotoDto photoDto = new PhotoDto();
        photoDto.setPhoto_id(photo.getId());
        photoDto.setImage_id(photo.getImage().getId());

        return photoDto;
    }
}
