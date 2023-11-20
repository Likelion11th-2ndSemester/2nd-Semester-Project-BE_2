package com.swuProject.secound.dto.request;

import com.swuProject.secound.domain.Photo.Photo;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PhotoUpdateDto { // 사진 수정
    @NotBlank(message="사진은 필수 입력 값입니다.")
    private Long image_id; // 등록 시 이미지 아이디 정보 저장
    private ImageFormDto imageFormDto; // 수정 시 이미지 정보 저장

    @NotBlank(message="촬영 날짜는 필수 입력 값입니다.")
    private String filmingDate;

    private Long album_id; // 앨범

    @NotBlank(message="사진관은 필수 입력 값입니다.")
    private Long studio_id; // 사진관

    @Max(value=50, message="최대 50자까지 작성 가능합니다.")
    private String content; // 메모

    private Boolean anonymous; // 익명 여부
    private Integer NumberOfPeople; // 인원수

    private List<Long> hashtageList = new ArrayList<>(); // 친구 해시태그

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO -> 엔티티 변환
    public Photo updatePhoto() {
        return modelMapper.map(this, Photo.class);
    }
}
