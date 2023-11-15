package com.swuProject.secound.article.entity;

import com.swuProject.secound.article.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDateTime regTime;

    @Column(nullable = true)
    private String postImage;

    @Enumerated(EnumType.STRING)
    private Category category;

    @PrePersist
    public void prePersist() {
        this.regTime = LocalDateTime.now();
    }

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
        if (article.postImage != null)
            this.postImage = article.postImage;
        if (article.category != null)
            this.category = article.category;
    }
}
