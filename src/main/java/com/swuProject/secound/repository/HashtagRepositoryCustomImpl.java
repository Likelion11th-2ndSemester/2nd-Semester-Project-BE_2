package com.swuProject.secound.repository;

import com.swuProject.secound.domain.Photo.Photo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class HashtagRepositoryCustomImpl implements HashtagRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Photo> findPhotosByMemberUsername(String username) {
        // 유저명 파라미터를 가진 멤버와 매핑된 해시태그 객체의 사진 엔티티 조회
        String jpql = "SELECT h.photo FROM Hashtag h WHERE h.member.name = :username";
        TypedQuery<Photo> query = entityManager.createQuery(jpql, Photo.class);
        query.setParameter("username", username);
        return query.getResultList();
    }
}
