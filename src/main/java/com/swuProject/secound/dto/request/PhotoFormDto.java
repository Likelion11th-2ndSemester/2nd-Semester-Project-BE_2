package com.swuProject.secound.dto.request;

import com.swuProject.secound.domain.Photo;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter @Setter
public class PhotoFormDto { // 작성, 수정

    private Long id;

    @NotBlank(message="촬영 날짜는 필수 입력 값입니다.")
    private LocalDate filmingDate;

    private String content;
    private Boolean anonymous;
    private Integer NumberOfPeople;

    // default 값 지정을 위한 생성자
    public void PhotoFormDto() {
        anonymous = true;
        NumberOfPeople = 0;
    }

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO -> 엔티티 변환
    public Photo createPhoto() {
        return modelMapper.map(this, Photo.class);
    }



}
