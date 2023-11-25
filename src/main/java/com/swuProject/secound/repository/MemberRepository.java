package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Member findByName(String username);

    Optional<Member> findByEmailOrNickname(String email, String nickname);

}
