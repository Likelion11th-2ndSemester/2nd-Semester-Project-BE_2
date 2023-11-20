package com.swuProject.secound.service;

import Album;
import com.swuProject.secound.dto.request.AlbumFormDto;
import com.swuProject.secound.dto.response.AlbumAllReturnDto;
import com.swuProject.secound.dto.response.AlbumReturnDto;
import com.swuProject.secound.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    // 앨범 생성
    public Long createAlbum(AlbumFormDto albumFormDto) {
        Album album = albumFormDto.createAlbum();

        // id는 DB가 자동 생성 하므로 사용자에게 입력 받을 필요가 없다.
        // 입력이 들어온 경우(= null이 아닌경우) null 반환
        if (album.getId() != null) {
            return null;
        }

        albumRepository.save(album);
        return album.getId();
    }

    // 앨범 수정
    public Long updateAlbum(Long album_id, AlbumFormDto albumFormDto) {
        Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);

        // 대상 엔티티가 없거나 url로 요청한 id와 바디로 담긴 수정하려는 게시글의 id가 다를 경우 처리
        if (album == null || album_id != album.getId()) {
            return null;
        }

        album.updateAlbum(albumFormDto);
        return album.getId();
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

    // 앨범 상세 조회
    @Transactional(readOnly = true)
    public AlbumReturnDto getAlbumDetail(Long album_id) {
        Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);
        AlbumReturnDto albumReturnDto = AlbumReturnDto.AlbumMapper(album);

        return albumReturnDto;
    }
}
