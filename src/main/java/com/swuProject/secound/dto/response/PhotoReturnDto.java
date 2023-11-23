package com.swuProject.secound.dto.response;

import com.swuProject.secound.domain.Member.Hashtag;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.dto.request.ImageFormDto;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PhotoReturnDto { // 사진 상세 조회, 사진 등록, 사진 수정

    private Long photo_id;
    private ImageFormDto imageFormDto;
    private String filmingDate;
    private Long album_id;
    private Long studio_id;
    private String content;
    private Boolean anonymous;
    private Integer numberOfPeople;
    private String username;
    private List<Long> taggedUserList = new ArrayList<>(); // 태그된 유저 아이디 배열

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 -> DTO 변환
    public static PhotoReturnDto PhotoMapper(Photo photo) {

        PhotoReturnDto photoReturnDto = new PhotoReturnDto();

        photoReturnDto.setPhoto_id(photo.getId());
        photoReturnDto.setFilmingDate(photo.getFilmingDate().toString());
        photoReturnDto.setAlbum_id(photo.getAlbumId());
        //photoReturnDto.setStudio_id(photo.getStudioId());
        photoReturnDto.setContent(photo.getContent());
        photoReturnDto.setAnonymous(photo.getAnonymous());
        photoReturnDto.setNumberOfPeople(photo.getNumberOfPeople());
        photoReturnDto.setUsername(photo.getUsername());

        // 해시태그 리스트 -> 매핑된 유저 아이디 리스트로 변환 후 세팅
        photoReturnDto.setTaggedUserList(photo.getTaggedUserList());

        return photoReturnDto;
    }
}
