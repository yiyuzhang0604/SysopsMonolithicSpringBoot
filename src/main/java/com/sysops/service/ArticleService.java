package com.sysops.service;

import com.sysops.entity.Article;

import java.util.List;

/**
 * ArticleService is responsible for managing article-related operations.
 * It provides an interface to create and search articles.
 */
public interface ArticleService {
    /**
     * Create a new article with given information
     *
     * @param article The article object containing article's information
     * @return The created article object
     */
    Article createArticle(Article article);

    /**
     * Search all the articles that contains the keyword, either in title or content
     *
     * @param keyword the keyword for searching
     * @return A list of articles that contain the keyword
     */
    List<Article> searchArticles(String keyword);
}
