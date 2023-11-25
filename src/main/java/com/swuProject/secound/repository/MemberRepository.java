package com.swuProject.secound.repository;

import com.swuProject.secound.article.entity.Article;
import com.swuProject.secound.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Member findByName(String username);

    Optional<Member> findByEmailOrNickname(String EmailOrNickname, String searchTerm);

}
