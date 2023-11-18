package com.swuProject.secound.comment.entity;

import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.comment.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    @Column
    private LocalDateTime regTime;

    @PrePersist
    public void prePersist() {
        this.regTime = LocalDateTime.now();
    }

    public static Comment createComment(CommentDto dto, Article article) {

        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody(),
                dto.getRegTime()
        );
    }
}
