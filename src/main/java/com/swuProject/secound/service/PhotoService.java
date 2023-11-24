package com.swuProject.secound.service;

import com.swuProject.secound.domain.Member.Hashtag;
import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Album;
import com.swuProject.secound.domain.Photo.Image;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Studio.Studio;
import com.swuProject.secound.dto.request.PhotoFormDto;
import com.swuProject.secound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final StudioRepository studioRepository;
    private final MemberRepository memberRepository;
    private final AlbumRepository albumRepository;
    private final HashtagRepository hashtagRepository;
    private final ImageService imageService;

    // 사진 생성
    public Long createPhoto(PhotoFormDto photoFormDto, String email, MultipartFile imgFile) throws Exception {

        try {
            // 사진 등록 - 연관관계 제외 필드 채움
            Photo photo = photoFormDto.createPhoto();

            // 유저 매핑
            Member member = memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

            // 앨범 매핑
            Long album_id = photoFormDto.getAlbum_id();
            Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);

            // 사진관 매핑
//            Long studio_id = photoFormDto.getStudio_id();
//            Studio studio = studioRepository.findById(studio_id).orElseThrow(EntityNotFoundException::new);
            // 해시태그 객체 생성

            List<Long> hashtageList = photoFormDto.getTaggedUserList();

            // 태그된 유저 아이디 루프 돌며 해당 아이디의 유저 조회
            for (Long memberId : hashtageList) {

                Member hashtagMember = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
                Hashtag hashtag = new Hashtag(hashtagMember, photo);
                hashtagRepository.save(hashtag);
            }

            // 사진관 평가 매핑


            // 사진관 리뷰 매핑


            // 사진관 저장


            // 이미지 등록
            Image image = new Image();

            // 연관관계 세팅
            photo.setMapping(member, album, null, image);
            photoRepository.save(photo);

            // 이미지 저장
            imageService.saveImg(image, imgFile);

            return photo.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 사진 수정
    public Long updatePhoto(Long id, PhotoFormDto photoFormDto) throws Exception {
//
//        // 게시글 찾기
//        Photo findPhoto = photoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//
//
         return null;
      }

}
