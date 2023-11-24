package com.swuProject.secound.article.service;

import com.swuProject.secound.article.dto.ArticleForm;
import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.article.repository.ArticleRepository;
import com.swuProject.secound.comment.dto.CommentDto;
import com.swuProject.secound.comment.repository.CommentRepository;
import com.swuProject.secound.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Article> index() {
        return articleRepository.findAllByOrderByRegTimeDesc();
    }

    public List<Article> searchPosts(String searchTerm) {
        // Assuming you have a method in your repository to search articles based on a term
        // Adjust the method name accordingly based on your actual repository
        return articleRepository.findByTitleContainingOrContentContainingOrderByRegTimeDesc(searchTerm, searchTerm);
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        article.setMember(member);

        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }

        // Check if the authenticated user is the author of the article
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!target.getMember().getUsername().equals(userDetails.getUsername())) {
            log.info("권한이 없습니다. id: {}, article: {}", id, article.toString());
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        // Check if the authenticated user is the author of the article
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!target.getMember().getUsername().equals(userDetails.getUsername())) {
            log.info("권한이 없습니다. id: {}", id);
            return null;
        }

        articleRepository.delete(target);
        return target;
    }

    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articleList = dtos.stream()
                .map(dto -> {
                    Article article = dto.toEntity();

                    Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    article.setMember(member);
                    return article;
                })
                .collect(Collectors.toList());

        articleList.forEach(article -> articleRepository.save(article));

        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("작성 실패!"));

        return articleList;
    }
}
