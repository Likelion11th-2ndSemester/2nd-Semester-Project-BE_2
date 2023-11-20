package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Album;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class AlbumAllReturnDto { // 앨범 전체 조회, 앨범 생성, 앨범 수정, 앨범 전체 조회 - 친구 검색

    private Long album_id;
    private String albumName;
    private String color;

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static AlbumAllReturnDto AlbumMapper(Album album) {

        return modelMapper.map(album, AlbumAllReturnDto.class);
    }
}
