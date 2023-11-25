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

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> searchEmailOrNickname(String searchTerm) {
        return memberRepository.findByEmailOrNickname(searchTerm, searchTerm);
    }
}
