package com.swuProject.secound.comment.service;

import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.article.repository.ArticleRepository;
import com.swuProject.secound.comment.dto.CommentDto;
import com.swuProject.secound.comment.entity.Comment;
import com.swuProject.secound.comment.repository.CommentRepository;
import com.swuProject.secound.domain.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // 게시글 리파지터리가 있어야 댓글을 생성할 때 대상 게시글의 존재 여부를 파악할 수 있음.

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {

        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));

        Comment comment = Comment.createComment(dto, article);

        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setMember(member);

        Comment created = commentRepository.save(comment);
        return CommentDto.createCommentDto(created);
    }
}
