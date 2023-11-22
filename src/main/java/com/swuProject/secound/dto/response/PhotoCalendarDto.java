package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Photo;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PhotoCalendarDto { // 사진 전체 조회 - 달력

    private String filmingDate;

    private List<PhotoDto> photoList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static PhotoCalendarDto PhotoMapper(String filmingDate, List<PhotoDto> photoDtoList) {

        PhotoCalendarDto photoCalendarDto = new PhotoCalendarDto();
        photoCalendarDto.setFilmingDate(filmingDate);
        photoCalendarDto.setPhotoList(photoDtoList);

        return photoCalendarDto;
    }
}
