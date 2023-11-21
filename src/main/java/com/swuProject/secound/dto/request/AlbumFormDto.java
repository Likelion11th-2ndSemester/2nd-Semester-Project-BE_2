package com.swuProject.secound.dto.request;

import com.swuProject.secound.domain.Photo.Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
@AllArgsConstructor
public class AlbumFormDto { // 앨범 생성, 앨범 수정

    private Long id; // 자동 생성

    @NotBlank(message = "앨범명은 필수 입력값 입니다.")
    private String albumName;

    @Size(max = 6, message="색상코드를 작성해주세요.")
    private String color;

    private final ModelMapper modelMapper = new ModelMapper();

    public AlbumFormDto() {
        color = "FF7F00"; // 기본값 주황색
    }


    // DTO -> 엔티티 변환
    public Album createAlbum() {
        return modelMapper.map(this, Album.class);
    }
}
