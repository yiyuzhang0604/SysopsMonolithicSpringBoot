package com.sysops.dao;

import com.sysops.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleDao extends CrudRepository<Article, Long> {
    @Query("SELECT article FROM Article a WHERE a.title LIKE %:keyword% OR a.content LIKE %:keyword%")
    List<Article> findByKeyword(@Param("keyword") String keyword);
}
