package com.swuProject.secound.article.dto;

import com.swuProject.secound.article.constant.Category;
import com.swuProject.secound.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;

    // 입력 폼에서 전송 받을 필드
    private String title;
    private String content;
    private String regTime;
    private String postImage; // nullable
    private Category category;

    public Article toEntity() {
        return new Article(id, title, content, regTime, postImage, category);
    }
}
