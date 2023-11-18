package com.swuProject.secound.article.api;

import com.swuProject.secound.article.dto.ArticleForm;
import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.article.service.ArticleService;
import com.swuProject.secound.comment.dto.CommentDto;
import com.swuProject.secound.comment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    //GET
    @GetMapping("/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleDetails> show(@PathVariable Long id) {

        Article article = articleService.show(id);
        List<CommentDto> comments = articleService.comments(id);

        ArticleDetails articleDetails = new ArticleDetails(article, comments);

        return ResponseEntity.ok(articleDetails);
    }


    //POST
    @PostMapping("/articles")
    public ResponseEntity<Article> creat(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/transaction-test")

    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}