package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Member.Hashtag;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.repository.HashtagRepository;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PhotoReturnDto { // 사진 상세 조회, 사진 등록, 사진 수정

    private Long photo_id;
    private Long image_id;
    private String filmingDate;
    private Long album_id;
    private Long studio_id;
    private String content;
    private Boolean anonymous;
    private Integer numberOfPeople;
    private String username;

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static PhotoReturnDto PhotoMapper(Photo photo) {

        PhotoReturnDto photoReturnDto = new PhotoReturnDto();

        photoReturnDto.setPhoto_id(photo.getId());
        photoReturnDto.setImage_id(photo.getImageId());
        photoReturnDto.setFilmingDate(photo.getFilmingDate().toString());
        photoReturnDto.setAlbum_id(photo.getAlbumId());
        //photoReturnDto.setStudio_id(photo.getStudioId());
        photoReturnDto.setContent(photo.getContent());
        photoReturnDto.setAnonymous(photo.getAnonymous());
        photoReturnDto.setNumberOfPeople(photo.getNumberOfPeople());
        photoReturnDto.setUsername(photo.getUsername());

        return photoReturnDto;
    }
}
