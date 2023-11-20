package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Photo.Album;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AlbumReturnDto { // 앨범 상세 조회, 앨범 상세 조회 - 친구 검색

    private Long album_id;
    private String albumName;
    private String color;
    private List<PhotoDto> photoDtoList = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static AlbumReturnDto AlbumMapper(Album album) {

        return modelMapper.map(album, AlbumReturnDto.class);
    }
}
