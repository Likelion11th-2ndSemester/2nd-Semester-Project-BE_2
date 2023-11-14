package com.swuProject.secound.domain;

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

    // 일대다 양방향 - 사진
    @OneToMany(mappedBy = "album")
    List<Photo> photoList = new ArrayList<>();

}
