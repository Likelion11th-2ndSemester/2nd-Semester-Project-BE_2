package com.swuProject.secound.article.dto;

import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.comment.dto.CommentDto;
import lombok.Data;

import java.util.List;

@Data
public class ArticleDetails {
    private Article article;
    private List<CommentDto> comments;

    public ArticleDetails(Article article, List<CommentDto> comments) {
        this.article = article;
        this.comments = comments;
    }
}