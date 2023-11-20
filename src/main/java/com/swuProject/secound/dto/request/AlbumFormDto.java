package com.swuProject.secound.dto.request;

import Album;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
@AllArgsConstructor
public class AlbumFormDto { // 앨범 생성, 앨범 수정

    private Long album_id; // 자동 생성
    private String albumName;
    private String color;

    private final ModelMapper modelMapper = new ModelMapper();

    // DTO -> 엔티티 변환
    public Album createAlbum() {

        return modelMapper.map(this, Album.class);
    }
}
