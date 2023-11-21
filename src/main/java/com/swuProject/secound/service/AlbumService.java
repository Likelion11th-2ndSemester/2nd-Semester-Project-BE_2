package com.swuProject.secound.service;


import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Album;
import com.swuProject.secound.domain.Photo.Photo;
import com.swuProject.secound.dto.request.AlbumFormDto;
import com.swuProject.secound.dto.response.AlbumAllReturnDto;
import com.swuProject.secound.dto.response.AlbumReturnDto;
import com.swuProject.secound.repository.AlbumRepository;
import com.swuProject.secound.repository.HashtagRepositoryCustom;
import com.swuProject.secound.repository.HashtagRepositoryCustomImpl;
import com.swuProject.secound.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private HashtagRepositoryCustomImpl hashtagRepositoryCustomImpl;

    // 앨범 생성
    public Long createAlbum(AlbumFormDto albumFormDto, String email) {
        Album album = albumFormDto.createAlbum();

        // id는 DB가 자동 생성 하므로 사용자에게 입력 받을 필요가 없다.
        // 입력이 들어온 경우(= null이 아닌경우) null 반환
        if (album.getId() != null) {
            return null;
        }

        // 앨범 생성자 할당
        Member member = memberRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);
        album.setMember(member);

        albumRepository.save(album);
        return album.getId();
    }

    // 앨범 수정
    public Long updateAlbum(Long album_id, AlbumFormDto albumFormDto) {
        // DTO -> 엔티티 변환
        Album album = albumFormDto.createAlbum();

        // 조회한 앨범이 존재하는지 확인
        Album target = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);

        // 대상 엔티티가 없거나 url로 요청한 id와 바디로 담긴 수정하려는 게시글의 id가 다를 경우 처리
        if (target == null || album_id != album.getId()) {
            return null;
        }

        // 앨범 수정
        target.updateAlbum(album);
        Album updated = albumRepository.save(target);

        return updated.getId();
    }

    // 앨범 삭제
    public void deleteAlbum(Long album_id) {
        Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);
        albumRepository.delete(album);
    }

    // 앨범 전체 조회
    @Transactional(readOnly = true)
    public List<AlbumAllReturnDto> getAlbumList() {
        List<Album> albumList = albumRepository.findAll();

        List<AlbumAllReturnDto> albumAllReturnDtos = new ArrayList<>();

        for (Album album : albumList) {
            AlbumAllReturnDto albumAllReturnDto = AlbumAllReturnDto.AlbumMapper(album);
            albumAllReturnDtos.add(albumAllReturnDto);
        }

        return albumAllReturnDtos;
    }

    // 앨범 전체 조회 - 검색한 친구명 포함 필터링
    public List<AlbumAllReturnDto> getAlbumListWithHashtag(String username) {
        // 1. 검색한 유저명을 가진 해시태그 객체와 연관된 사진 객체 조회
        List<Photo> photoList = hashtagRepositoryCustomImpl.findPhotosByMemberUsername(username);

        // 중복 제거하기 위해 HashSet 생성
        Set<Album> uniqueAlbumSet = new HashSet<>();

        // 2. photo와 연관된 앨범을 조회하여 중복 제거
        for (Photo photo : photoList) {

            Album album = photo.getAlbum();
            if (album != null) {
                uniqueAlbumSet.add(album);
            }
        }

        // 사진 객체를 가지고 있는 앨범 객체 배열
        List<Album> albumListWithHashtag = new ArrayList<>();

        // 중복 제거한 앨범 셋 리스트에 추가
        albumListWithHashtag.addAll(uniqueAlbumSet);

        // 엔티티 -> DTO 변환
        List<AlbumAllReturnDto> albumAllReturnDtos = new ArrayList<>();

        for (Album album : albumListWithHashtag) {
            AlbumAllReturnDto albumAllReturnDto = AlbumAllReturnDto.AlbumMapper(album);
            albumAllReturnDtos.add(albumAllReturnDto);
        }

        return albumAllReturnDtos;
    }

    // 앨범 상세 조회
    @Transactional(readOnly = true)
    public AlbumReturnDto getAlbumDetail(Long album_id) {
        Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);
        AlbumReturnDto albumReturnDto = AlbumReturnDto.AlbumMapper(album);

        return albumReturnDto;
    }


}
