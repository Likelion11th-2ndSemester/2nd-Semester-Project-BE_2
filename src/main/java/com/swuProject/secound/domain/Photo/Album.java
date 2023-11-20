package com.swuProject.secound.domain.Photo;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.dto.request.AlbumFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Album {

    @Id @GeneratedValue
    @Column(name="album_id")
    private Long id;

    private String albumName;

    private String color;

    // 다대일 단방향 - 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    // 일대다 양방향 - 사진
    @OneToMany(mappedBy = "album")
    List<Photo> photoList = new ArrayList<>();

    public Album(long id, String albumName, String color) {
    }

    // 앨범 수정
    public void updateAlbum(AlbumFormDto albumFormDto) {
        this.albumName = albumFormDto.getAlbumName();
        this.color = albumFormDto.getColor();
    }
}
