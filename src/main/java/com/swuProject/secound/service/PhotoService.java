package com.swuProject.secound.service;

import com.swuProject.secound.domain.*;
import com.swuProject.secound.dto.request.ImageFormDto;
import com.swuProject.secound.dto.request.PhotoFormDto;
import com.swuProject.secound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final StudioRepository studioRepository;
    private final MemberRepository memberRepository;
    private final AlbumRepository albumRepository;
    private final ImageService imageService;

    // 사진 생성
    public Long createPhoto(PhotoFormDto photoFormDto, MultipartFile imgFile,
                            Long albumId, String studioName, String email) throws Exception {

        // 앨범 조회하기
        Album album = albumRepository.findByAlbumId(albumId);

        // 사진관 조회하기
        Studio studio = studioRepository.findByStudioName(studioName);

        // 사용자 조회하기
        Member member = memberRepository.findByEmail(email);

        // 사진 등록
        Photo photo = photoFormDto.createPhoto(); // DTO -> 엔티티 변환
        photo.setAlbum(album);
        photo.setStudio(studio);
        photo.setMember(member);

        photoRepository.save(photo);

        // 이미지 등록
        Image image = new Image();
        image.setPhoto(photo);
        imageService.saveImage(image, imgFile);

        return photo.getId();
    }

    // 사진 수정
//    public Long updatePhoto(Long id, PhotoFormDto photoFormDto) throws Exception {
//
//        // 게시글 찾기
//        Photo findPhoto = photoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//
//
//
//    }

}
