package com.sysops.service.Implementation;

import com.sysops.dao.ArticleDao;
import com.sysops.entity.Article;
import com.sysops.entity.Expert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {
    @Mock
    private ArticleDao articleDao;
    private ArticleServiceImpl articleService;

    @BeforeEach
    void setUp() {
        articleService = new ArticleServiceImpl(articleDao);
    }

    @Test
    public void testCreateArticle() {
        Expert expert = new Expert("123-456-7890", "Seattle");
        Article inputArticle = new Article(expert, "Input Title", "Input Content", 1);
        Article savedArticle = new Article(expert, "Saved Title", "Saved Content", 1);

        when(articleDao.save(inputArticle)).thenReturn(savedArticle);

        Article createdArticle = articleService.createArticle(inputArticle);

        assertEquals(savedArticle, createdArticle);
        verify(articleDao, times(1)).save(inputArticle);
        verifyNoMoreInteractions(articleDao);
    }

    @Test
    public void testSearchArticles() {
        Expert expert = new Expert("123-456-7890", "Seattle");
        Article article1 = new Article(expert, "article1", "This article contains the keyword 'testing'", 1);
        List<Article> articles = new ArrayList<>();
        articles.add(article1);

        when(articleDao.findByKeyword("testing")).thenReturn(articles);

        List<Article> result = articleService.searchArticles("testing");

        assertEquals(articles, result);
        verify(articleDao, times(1)).findByKeyword("testing");
        verifyNoMoreInteractions(articleDao);
    }

}
