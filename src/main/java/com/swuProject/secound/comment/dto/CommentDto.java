package com.swuProject.secound.comment.dto;

import com.swuProject.secound.comment.entity.Comment;
import com.swuProject.secound.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private Long articleId;
    private String body;
    private LocalDateTime regTime;
    private Member member;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getBody(),
                comment.getRegTime(),
                comment.getMember()
                );
    }
}
