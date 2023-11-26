package com.swuProject.secound.domain.Member;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private Member friend;

    public Friends() {
    }

    public Friends(Member member, Member friend) {
        this.member = member;
        this.friend = friend;
    }
}
