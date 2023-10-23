package com.swuProject.secound.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody Map<String, String> user) {
        return memberRepository.save(Member.builder()
                .email(user.get("email"))
                .name(user.get("name"))
                .nickname(user.get("nickname"))
                .pn(user.get("pn"))
                .password(passwordEncoder.encode(user.get("password")))
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .build()).getId();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        Member member = memberRepository.findByEmail(user.get("email"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

    @GetMapping("/user/name/{id}")
    public Map<String, String> get(@PathVariable Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        Map<String, String> response = new HashMap<>();
        if (member != null) {
            response.put("name", member.getName());
            response.put("pn", member.getPn());
            response.put("nickname", member.getNickname());
        } else {
            response.put("error", "Member not found");
        }
        return response;
    }

}
