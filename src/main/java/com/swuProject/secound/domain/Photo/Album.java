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
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<Photo> photoList = new ArrayList<>();

    // 앨범 수정 (부분 수정도 가능)
    public void updateAlbum(Album album) {
        if (album.albumName != null)
            this.albumName = album.albumName;
        if (album.color != null)
            this.color = album.color;
    }

}
