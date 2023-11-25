package com.swuProject.secound.controller;

import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.repository.MemberRepository;
import com.swuProject.secound.service.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class InviteController {

    @Autowired
    private InviteService inviteService;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/mypage")
    public Optional<Member> searchEmail(@RequestParam(name = "Email") String searchTerm) {
        return inviteService.searchEmail(searchTerm);
    }

    @PostMapping("/mypage")
    public ResponseEntity<String> addFriend(@RequestParam(name = "Email") String searchTerm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Member currentUser = memberRepository.findByName(currentUsername);

        // Find the user to be friends with
        Member friend = memberRepository.findByEmail(searchTerm).orElse(null);

        if (currentUser != null && friend != null) {
            inviteService.addFriend(currentUser, friend);
            return ResponseEntity.ok("친구가 성공적으로 추가되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 사용자 또는 친구 ID입니다.");
        }
    }

    @DeleteMapping("/mypage")
    public ResponseEntity<String> removeFriend(@RequestParam(name = "Email") String searchTerm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Member currentUser = memberRepository.findByName(currentUsername);

        // Find the user to be removed as a friend
        Member friend = memberRepository.findByEmail(searchTerm).orElse(null);

        if (currentUser != null && friend != null) {
            inviteService.removeFriend(currentUser, friend);
            return ResponseEntity.ok("친구가 성공적으로 삭제되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 사용자 또는 친구 ID입니다.");
        }
    }
}
