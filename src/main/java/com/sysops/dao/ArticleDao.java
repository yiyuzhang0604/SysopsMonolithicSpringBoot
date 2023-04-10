package com.sysops.dao;

import com.sysops.entity.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO for managing Article entities.
 */
public interface ArticleDao extends CrudRepository<Article, Long> {
    /**
     * Search for articles that contain the keyword.
     * @param keyword keyword to search
     * @return list of articles that contain the keyword
     */
    @Query("SELECT a FROM Article a WHERE a.title LIKE %:keyword% OR a.content LIKE %:keyword%")
    List<Article> findByKeyword(@Param("keyword") String keyword);
}
