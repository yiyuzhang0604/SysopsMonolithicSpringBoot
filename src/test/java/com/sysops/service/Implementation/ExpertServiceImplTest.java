package com.sysops.service.Implementation;

import com.sysops.dao.ExpertDao;
import com.sysops.entity.Article;
import com.sysops.entity.Customer;
import com.sysops.entity.Expert;
import com.sysops.entity.Ticket;
import com.sysops.exceptions.ExpertNotFoundException;
import com.sysops.exceptions.NoMatchingExpertFoundException;
import com.sysops.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ExpertServiceImplTest {

    @Mock
    private ExpertDao expertDao;
    @Mock
    private ArticleService articleService;
    private ExpertServiceImpl expertService;

    @BeforeEach
    void setUp() {
        expertService = new ExpertServiceImpl(expertDao, articleService);
    }

    @Test
    public void getNumberByExpertIdTest() {
        Long expertId = 1L;
        String phoneNumber = "123-456-7890";
        Expert expert = new Expert(phoneNumber, "Seattle");

        when(expertDao.findById(expertId)).thenReturn(Optional.of(expert));

        String returnedPhoneNumber = expertService.getNumberByExpertId(expertId);

        assertEquals(phoneNumber, returnedPhoneNumber);
        verify(expertDao, times(1)).findById(expertId);
        verifyNoMoreInteractions(expertDao);
    }

    @Test
    public void getExpertById_success() {
        Long expertId = 1L;
        Expert expert = new Expert("123-456-7890", "Seattle");

        when(expertDao.findById(expertId)).thenReturn(Optional.of(expert));

        Expert result = expertService.getExpertById(expertId);

        assertEquals(expert, result);
        verify(expertDao, times(1)).findById(expertId);
        verifyNoMoreInteractions(expertDao);
    }

    @Test
    public void getExpertById_ExpertNotFoundException() {
        Long expertId = 1L;

        when(expertDao.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ExpertNotFoundException.class, () -> expertService.getExpertById(expertId));
        verify(expertDao, times(1)).findById(expertId);
        verifyNoMoreInteractions(expertDao);
    }

    @Test
    public void findMatchExpert_success() {
        Expert expert1 = new Expert("123-456-7890", "Seattle");
        Expert expert2 = new Expert("098-765-4321", "Seattle");
        Customer customer = new Customer("111-111-111", "email", false);
        Ticket ticket = new Ticket(1, customer, "description", "Seattle");
        List<Expert> matchedExperts = Arrays.asList(expert1, expert2);

        when(expertDao.findMatchedExperts(anyInt(), anyString())).thenReturn(matchedExperts);

        List<Expert> foundExperts = expertService.findMatchExpert(ticket);

        assertEquals(matchedExperts, foundExperts);
        verify(expertDao, times(1)).findMatchedExperts(ticket.getCategoryId(), ticket.getLocation());
        verifyNoMoreInteractions(expertDao);
    }

    @Test
    public void findMatchExpert_NoMatchingExpertFoundException() {
        Customer customer = new Customer("111-111-111", "email", false);
        Ticket ticket = new Ticket(1, customer, "description", "Seattle");

        when(expertDao.findMatchedExperts(anyInt(), anyString())).thenReturn(new ArrayList<>());

        assertThrows(NoMatchingExpertFoundException.class, () -> expertService.findMatchExpert(ticket));
        verify(expertDao, times(1)).findMatchedExperts(ticket.getCategoryId(), ticket.getLocation());
        verifyNoMoreInteractions(expertDao);
    }

    @Test
    public void getFirstAvailableExpertTest() {
        Expert expert = new Expert("123-456-7890", "Seattle");
        List<Expert> matchedExperts = Collections.singletonList(expert);
        Expert firstExpert = expertService.getFirstAvailableExpert(matchedExperts);
        assertEquals(expert, firstExpert);
    }

    @Test
    public void getAllExpertsTest() {
        Expert expert1 = new Expert("123-456-7890", "Seattle");
        Expert expert2 = new Expert("098-765-4321", "Seattle");
        List<Expert> matchedExperts = Arrays.asList(expert1, expert2);

        when(expertDao.findAll()).thenReturn(matchedExperts);

        List<Expert> result = expertService.getAllExperts();

        assertEquals(matchedExperts, result);
        verify(expertDao, times(1)).findAll();
        verifyNoMoreInteractions(expertDao);
    }

    @Test
    public void registerExpertTest() {
        Expert inputExpert = new Expert("123-456-7890", "Seattle");
        Expert savedExpert = new Expert("123-456-7890", "Seattle");

        when(expertDao.save(inputExpert)).thenReturn(savedExpert);

        Expert result = expertService.register(inputExpert);

        assertEquals(savedExpert, result);
        verify(expertDao, times(1)).save(inputExpert);
        verifyNoMoreInteractions(expertDao);
    }

    @Test
    public void createArticleForExpertTest() {
        Expert expert = new Expert("123-456-7890", "Seattle");
        Article article = new Article(expert, "title", "content", 1);
        Long expertId = expert.getExpertId();

        when(expertDao.findById(expertId)).thenReturn(Optional.of(expert));
        when(articleService.createArticle(article)).thenReturn(article);

        Article createdArticle = expertService.createArticleForExpert(article, expertId);

        assertEquals(article, createdArticle);
        verify(expertDao, times(1)).findById(expertId);
        verify(articleService, times(1)).createArticle(article);
        verifyNoMoreInteractions(expertDao, articleService);
    }

}
