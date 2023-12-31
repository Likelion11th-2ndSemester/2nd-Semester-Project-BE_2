package com.swuProject.secound.article.dto;

import com.swuProject.secound.article.constant.Category;
import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.domain.Photo.Image;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;

    // 입력 폼에서 전송 받을 필드
    private String title;
    private String content;
    private LocalDateTime regTime;
    private Image image;
    private Category category;
    private Member member;

    public Article toEntity() {
        return new Article(id, title, content, regTime, image, category, member);
    }
}
