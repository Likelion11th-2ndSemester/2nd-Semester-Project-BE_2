package com.swuProject.secound.controller;

import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InviteController {

    @Autowired
    private InviteService inviteService;

    @GetMapping("/mypage")
    public List<Member> searchEmailOrNickname(@RequestParam(name = "EmailOrNickname") String searchTerm) {
        return inviteService.searchEmailOrNickname(searchTerm);
    }
}
