package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Photo;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class PhotoPublicDto { // 공개된 사진 전체 조회, 공개된 사진 상세 조회, 인원 수 필터링

    private Long photo_id;
    private Long image_id;
    private Integer numberOfPeople;
    private Boolean scrap;

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static PhotoPublicDto PhotoMapper(Photo photo) {

        return modelMapper.map(photo, PhotoPublicDto.class);
    }
}
