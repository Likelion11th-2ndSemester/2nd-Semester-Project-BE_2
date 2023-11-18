package com.swuProject.secound.comment.api;

import com.swuProject.secound.comment.dto.CommentDto;
import com.swuProject.secound.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

//    @GetMapping("/articles/{articleId}")
//    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
//        // 서비스에 위임
//        List<CommentDto> dtos = commentService.comments(articleId); // service의 메서드와 컨트롤러 메서드의 이름을 일부러 같게 지음
//        // 결과 응답
//        return ResponseEntity.status(HttpStatus.OK).body(dtos);
//    }

    @PostMapping("/articles/{articleId}")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto) {

        CommentDto createdDto = commentService.create(articleId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
}
