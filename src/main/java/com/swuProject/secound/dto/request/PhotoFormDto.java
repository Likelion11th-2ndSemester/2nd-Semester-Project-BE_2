package com.swuProject.secound.dto.request;

import com.swuProject.secound.domain.Photo.Photo;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PhotoFormDto { // 사진 등록

    private Long id;

    private ImageFormDto imageFormDto; // 수정 시 이미지 정보 저장

    @NotBlank(message="촬영 날짜는 필수 입력 값입니다.")
    private String filmingDate;

    private Long album_id; // 앨범

    private Long studio_id; // 사진관

    @Size(max=50, message="최대 50자까지 작성 가능합니다.")
    private String content; // 메모

    private Boolean anonymous; // 익명 여부
    private Integer numberOfPeople; // 인원수

    private List<Long> taggedUserList = new ArrayList<>(); // 친구 해시태그

    // 평가
    private Integer satisfaction; // 만족도
    private Integer cleanliness; // 청결도
    private Integer management; // 관리상태

    @Size(max=50, message="최대 50자까지 작성 가능합니다.")
    private String review; // 사진관 리뷰

//    // default 값 지정을 위한 생성자
//    public void PhotoFormDto() {
//        anonymous = true; // 익명이 default
//        numberOfPeople = 0; // 인원수 미선택 시 default = 0
//        album_id = 0L; // 앨범 미선택 시 미분류 앨범 (id = 0)
//    }


    // DTO -> 엔티티 변환
    public Photo createPhoto() {
        LocalDate filmingDateType = LocalDate.parse(filmingDate);
        return new Photo(id, filmingDateType, content, anonymous, numberOfPeople);
    }

}
