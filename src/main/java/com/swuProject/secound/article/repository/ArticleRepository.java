package com.swuProject.secound.article.repository;

import com.swuProject.secound.article.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    List<Article> findAllByOrderByRegTimeDesc();

    List<Article> findByTitleContainingOrContentContainingOrderByRegTimeDesc(String searchTerm, String searchTerm1);
}