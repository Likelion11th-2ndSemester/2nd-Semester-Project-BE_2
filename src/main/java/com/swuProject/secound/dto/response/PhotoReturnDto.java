package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo;
import com.swuProject.secound.dto.request.PhotoFormDto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
public class PhotoReturnDto {

    private Long id;
    private LocalDate filmingDate;
    private String content;
    private Boolean anonymous;
    private Integer NumberOfPeople;

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static PhotoReturnDto PhotoMapper(Photo photo) {
        return modelMapper.map(photo, PhotoReturnDto.class);
    }
}
