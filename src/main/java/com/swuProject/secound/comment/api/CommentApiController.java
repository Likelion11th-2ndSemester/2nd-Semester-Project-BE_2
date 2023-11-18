package com.swuProject.secound.comment.api;

import com.swuProject.secound.comment.dto.CommentDto;
import com.swuProject.secound.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/articles/{articleId}")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) {

        CommentDto createdDto = commentService.create(articleId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
}
