package com.swuProject.secound.repository;

import com.swuProject.secound.article.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InviteRepository extends CrudRepository<Article, Long> {

    List<Article> findAllByOrderByRegTimeDesc();

    List<Article> findByTitleContainingOrContentContainingOrderByRegTimeDesc(String searchTerm, String searchTerm1);
}
