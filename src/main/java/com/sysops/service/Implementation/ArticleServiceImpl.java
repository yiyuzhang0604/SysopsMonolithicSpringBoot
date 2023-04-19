package com.sysops.service.Implementation;

import com.sysops.dao.ArticleDao;
import com.sysops.entity.Article;
import com.sysops.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of ArticleService
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public Article createArticle(Article article) {
        return articleDao.save(article);
    }

    @Override
    public List<Article> searchArticles(String keyword) {
        return articleDao.findByKeyword(keyword);
    }
}
