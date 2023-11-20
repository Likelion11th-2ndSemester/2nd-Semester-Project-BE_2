package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Photo;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PhotoCalendarDto { // 사진 전체 조회 - 달력

    private Integer year;
    private Integer month;
    private Integer date;

    private List<PhotoDto> photoDtoList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static PhotoCalendarDto PhotoMapper(Photo photo) {

        return modelMapper.map(photo, PhotoCalendarDto.class);
    }
}
