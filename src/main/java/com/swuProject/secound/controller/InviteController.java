package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.repository.MemberRepository;
import com.swuProject.secound.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class InviteController {

    private final InviteService inviteService;

    @Autowired
    public InviteController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @GetMapping("/{email}")
    public Member getMemberByEmail(@PathVariable String email) {
        return inviteService.findMemberByEmail(email);
    }

    @PostMapping("/add-friend")
    public ResponseEntity<String> addFriendByEmail(@RequestParam String userEmail, @RequestParam String friendEmail) {
        try {
            inviteService.addFriend(userEmail, friendEmail);
            return new ResponseEntity<>("Friendship added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
