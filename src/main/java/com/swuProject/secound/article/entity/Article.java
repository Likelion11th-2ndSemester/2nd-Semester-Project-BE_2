package com.swuProject.secound.article.entity;

import com.swuProject.secound.article.constant.Category;
import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Image;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter @Setter
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

    // 일대일 단방향 - 이미지
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="image_id", nullable = true)
    private Image image;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @PrePersist
    public void prePersist() {
        this.regTime = LocalDateTime.now();
    }

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
        if (article.image != null)
            this.image = article.image;
        if (article.category != null)
            this.category = article.category;
    }
}
