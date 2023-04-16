package com.sysops.service.Implementation;

import com.sysops.dao.ExpertDao;
import com.sysops.entity.Article;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;
import com.sysops.exceptions.ExpertNotFoundException;
import com.sysops.exceptions.NoMatchingExpertFoundException;
import com.sysops.service.ArticleService;
import com.sysops.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of ExpertService
 */
@Service
public class ExpertServiceImpl implements ExpertService {
    private final ExpertDao expertDao;
    private final ArticleService articleService;

    @Autowired
    public ExpertServiceImpl(ExpertDao expertDao, ArticleService articleService) {
        this.expertDao = expertDao;
        this.articleService = articleService;
    }

    @Override
    public String getNumberByExpertId(Long expertId) {
        Expert expert = getExpertById(expertId);
        return expert.getPhoneNumber();
    }

    @Override
    public Expert getExpertById(Long expertId) {
        return expertDao.findById(expertId).orElseThrow(() -> new ExpertNotFoundException("Expert not found with Id: " + expertId));
    }

    @Override
    public List<Expert> findMatchExpert(Ticket ticket) {
        List<Expert> experts = expertDao.findMatchedExperts(ticket.getCategoryId(), ticket.getLocation());
//        experts.sort(Comparator.comparingInt(e -> e.getTickets().size()));//tickets include not only assigned tickets but also resolved tickets so any available expert works fine for this. Keep the logic simple for now
        if (experts.isEmpty()) {
            throw new NoMatchingExpertFoundException("No expert available");
        }
        return experts;
    }

    @Override
    public Expert getFirstAvailableExpert(List<Expert> experts) {
        return experts.isEmpty() ? null : experts.get(0);
    }

    @Override
    public List<Expert> getAllExperts() {
        return (List<Expert>) expertDao.findAll();
    }

    @Override
    public Expert register(Expert expert) {
        return expertDao.save(expert);
    }

    @Override
    public Article createArticleForExpert(Article article, Long expertId) {
        Expert expert = getExpertById(expertId);
        article.setAuthor(expert);
        expert.addArticle(article);
        return articleService.createArticle(article);
    }
}
