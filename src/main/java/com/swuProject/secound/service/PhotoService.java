package com.swuProject.secound.service;

import com.swuProject.secound.domain.Member.Hashtag;
import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Album;
import com.swuProject.secound.domain.Photo.Image;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.domain.Photo.Scrap;
import com.swuProject.secound.domain.Studio.Studio;
import com.swuProject.secound.dto.request.PhotoFormDto;
import com.swuProject.secound.dto.request.PhotoUpdateDto;
import com.swuProject.secound.dto.response.*;
import com.swuProject.secound.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private final ScrapRepository scrapRepository;
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

            // 해시태그 매핑
            List<Long> hashtageList = photoFormDto.getTaggedUserList();

            // 태그된 유저 아이디 루프 돌며 해당 아이디의 유저 조회 후 해시태그 객체 생성
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
            photo.setNewMapping(member, album, null, image);
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
    public Long updatePhoto(Long photo_id, PhotoUpdateDto photoUpdateDto, MultipartFile imgFile) throws Exception {
        try {
            // DTO -> 엔티티 변환
            Photo photo = photoUpdateDto.updatePhoto();

            // 조회한 사진이 존재하는지 확인
            Photo target = photoRepository.findById(photo_id).orElseThrow(EntityNotFoundException::new);

            // 대상 엔티티가 없거나 url로 요청한 id와 바디로 담긴 수정하려는 게시글의 id가 다를 경우 처리
            if (target == null || photo_id != photo.getId()) {
                return null;
            }

            target.updatePhoto(photo);

            // 앨범 찾기
            Long album_id = photoUpdateDto.getAlbum_id();
            Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);

            // 사진관 매핑
             Long studio_id = photoUpdateDto.getStudio_id();
             Studio studio = studioRepository.findById(studio_id).orElseThrow(EntityNotFoundException::new);

            // 연관관계 수정 세팅
            target.setUpdateMapping(album, studio);

            // 이미지 수정
            Long image_id = photoUpdateDto.getImage_id();
            imageService.updateImg(image_id, imgFile);

            Photo updated = photoRepository.save(target);

            return updated.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      }

    // 사진 상세 조회
    @Transactional(readOnly = true)
    public PhotoReturnDto getPhotoDetail(Long photo_id) {
        Photo photo = photoRepository.findById(photo_id).orElseThrow(EntityNotFoundException::new);
        PhotoReturnDto photoReturnDto = PhotoReturnDto.PhotoMapper(photo);

        return photoReturnDto;
    }

    // 사진 삭제
    public void deletePhoto(Long photoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(EntityNotFoundException::new);
        photoRepository.delete(photo);
    }

    // 마이페이지 - 달력 에서 촬영 날짜별 등록된 사진 전체 조회
    public PhotoCalendarDto getPhotoCalendar(String filmingDate) {

        // String -> LocalDate 형변환
        LocalDate filmingDateType = LocalDate.parse(filmingDate);
        List<Photo> photoCalendarList = photoRepository.findByFilmingDate(filmingDateType);
        List<PhotoDto> photoDtoList = new ArrayList<>();

        // 사진 엔티티 -> PhotoDto로 변환
        for (Photo photo : photoCalendarList) {
            PhotoDto photoDto = PhotoDto.photoDtoMapper(photo);
            photoDtoList.add(photoDto);
        }

        PhotoCalendarDto photoCalendarDto = PhotoCalendarDto.PhotoMapper(filmingDate, photoDtoList);
        return photoCalendarDto;
    }


    public List<PhotoPublicDto> getPhotoPublic(String email) {

        Member member = memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        List<Photo> photoPublicList = photoRepository.findByAnonymousIsTrue();
        List<PhotoPublicDto> photoPublicDtoList = new ArrayList<>();

        for (Photo photo : photoPublicList) {
            Scrap scrap = scrapRepository.findByMemberAndPhoto(member, photo);

            if (scrap != null) { // 스크랩한 사진이라면
                PhotoPublicDto photoPublicDto = PhotoPublicDto.PhotoMapper(photo, true);
                photoPublicDtoList.add(photoPublicDto);
            } else { // 스크랩한 사진이 아니라면
                PhotoPublicDto photoPublicDto = PhotoPublicDto.PhotoMapper(photo, false);
                photoPublicDtoList.add(photoPublicDto);
            }
        }

        return photoPublicDtoList;
    }

    // 스크랩 / 스크랩 취소
    public void scrapStatus(String email, Photo photo) {
        Member member = memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        Scrap target = scrapRepository.findByMemberAndPhoto(member, photo);

        if (target != null) { // 이미 스크랩한 사진이라면 스크랩 삭제
            scrapRepository.delete(target);
        } else { // 스크랩한 사진이 아니라면 스크랩 생성
            Scrap scrap= new Scrap(member, photo);
            scrapRepository.save(scrap);
        }
    }
}
