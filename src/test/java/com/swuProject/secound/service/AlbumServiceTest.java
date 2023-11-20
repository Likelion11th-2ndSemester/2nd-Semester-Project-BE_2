package com.swuProject.secound.service;

import Album;
import com.swuProject.secound.dto.request.AlbumFormDto;
import com.swuProject.secound.repository.AlbumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@SpringBootTest
class AlbumServiceTest {

    @Autowired
    AlbumService albumService;

    @Autowired
    AlbumRepository albumRepository;

    // 앨범 생성 테스트
    @Test
    @DisplayName("앨범 생성 테스트")
    void createAlbum() {
        // 1. 예상 데이터
        String albumName = "앨범1";
        String color = "FFFFFF";
        AlbumFormDto albumFormDto = new AlbumFormDto(null, albumName, color);
        Album expected = new Album(1L, albumName, color);

        // 2. 실제 데이터
        Long album_id = albumService.createAlbum(albumFormDto);
        Album album = albumRepository.findById(album_id).orElseThrow(EntityNotFoundException::new);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), album.toString());
    }

    // 앨범 수정 테스트
    @Test
    void updateAlbum() {
    }

    // 앨범 삭제 테스트
    @Test
    void deleteAlbum() {
    }

    // 앨범 전체 조회 테스트
    @Test
    void getAlbumList() {
    }

    // 앨범 상세 조회 테스트
    @Test
    void getAlbumDetail() {
    }
}