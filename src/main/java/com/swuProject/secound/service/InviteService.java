package com.swuProject.secound.service;

import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.domain.Member.Member;
import com.swuProject.secound.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InviteService {

    private final MemberRepository memberRepository;

    @Autowired
    public InviteService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Member not found with email: " + email));
    }
}
