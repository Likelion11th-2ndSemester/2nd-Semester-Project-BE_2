package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Studio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class StudioWithPhotosDto {

    private Long studioId;
    private String studioName;

    private String phoneNumber;
    private String operatingTime;

    // api 사용
    private String address;
    private List<SimplePhotoDto> photos;

    public static StudioWithPhotosDto from(Studio studio, List<Photo> photoList) {
        StudioWithPhotosDto dto = new StudioWithPhotosDto();
        dto.setStudioId(studio.getId());
        dto.setStudioName(studio.getStudioName());
        dto.setPhoneNumber(studio.getPhoneNumber());
        dto.setAddress(studio.getAddress());
        dto.setOperatingTime(studio.getOperatingTime());

        List<SimplePhotoDto> simplePhotoDtoList = photoList.stream()
                .map(SimplePhotoDto::from)
                .collect(Collectors.toList());
        dto.setPhotos(simplePhotoDtoList);

        return dto;
    }
}
