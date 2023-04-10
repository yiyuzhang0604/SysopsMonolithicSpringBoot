package com.sysops.dao;

import com.sysops.entity.Article;
import com.sysops.entity.Expert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
public class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ExpertDao expertDao;

    @Test
    public void findByKeywordTest() {
        Expert expert = new Expert("123-456-7890", "Seattle");
        Article article1 = new Article(expert, "article1", "This article contains the keyword 'testing'", 1);
        Article article2 = new Article(expert, "article2", "This article contains no keyword", 1);
        expert.addArticle(article1);
        expert.addArticle(article2);
        expertDao.save(expert);
        articleDao.save(article1);
        articleDao.save(article2);
        List<Article> result = articleDao.findByKeyword("testing");
        assertThat(result).containsOnly(article1);
    }

}
