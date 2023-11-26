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

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public ResponseEntity<String> addFriendByEmail(@RequestParam(name = "friendEmail") String friendEmail) {
        try {
            // Get the currently authenticated user's email
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName(); // Assuming the email is the username

            inviteService.addFriend(userEmail, friendEmail);
            return new ResponseEntity<>("Friendship added successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/friends")
    public ResponseEntity<List<Long>> getFriendIdsForCurrentUser(Principal principal) {
        try {
            String userEmail = principal.getName();
            List<Member> friends = inviteService.getFriends(userEmail);

            // Extracting friend IDs from the list of friends
            List<Long> friendIds = friends.stream()
                    .map(Member::getId)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(friendIds, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/friends/{friendId}")
    public ResponseEntity<Void> deleteFriendForCurrentUser(@PathVariable Long friendId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userEmail = authentication.getName(); // Assuming the email is the username

            inviteService.deleteFriend(userEmail, friendId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
