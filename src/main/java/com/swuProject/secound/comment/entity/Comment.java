package com.swuProject.secound.comment.entity;

import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.comment.dto.CommentDto;
import com.swuProject.secound.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
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
    private String body;

    @Column
    private LocalDateTime regTime;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

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
                dto.getBody(),
                dto.getRegTime(),
                dto.getMember()
        );
    }
}
