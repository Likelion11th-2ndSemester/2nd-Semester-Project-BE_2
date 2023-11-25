package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InviteController {

    @Autowired
    private InviteService inviteService;

    @GetMapping("/mypage")
    public ResponseEntity<Member> getMemberByEmailOrNickname(
            @RequestParam(name = "emailOrNickname") String emailOrNickname) {
        // Log the incoming request
        System.out.println("Request received with emailOrNickname: " + emailOrNickname);

        return inviteService.findByEmailOrNickname(emailOrNickname)
                .map(member -> {
                    // Log the successful response
                    System.out.println("Response: " + member);
                    return ResponseEntity.ok(member);
                })
                .orElseGet(() -> {
                    // Log the not found response
                    System.out.println("Member not found for emailOrNickname: " + emailOrNickname);
                    return ResponseEntity.notFound().build();
                });
    }
}
