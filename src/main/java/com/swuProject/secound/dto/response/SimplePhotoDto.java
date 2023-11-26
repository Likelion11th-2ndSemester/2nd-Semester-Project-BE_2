package com.swuProject.secound.dto.response;
// SimplePhotoDto 클래스

import com.swuProject.secound.domain.Photo.Photo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimplePhotoDto {

    private Long photoId;
    private String imageUrl;  // 예시: 이미지 URL을 포함할 수 있음

    public static SimplePhotoDto from(Photo photo) {
        SimplePhotoDto dto = new SimplePhotoDto();
        dto.setPhotoId(photo.getId());
        // 이미지 URL을 설정하는 로직을 추가하면 됩니다.
        dto.setImageUrl(photo.getImage().getImgPath());  // 예시: Image 엔티티의 이미지 경로를 사용
        return dto;
    }
}
