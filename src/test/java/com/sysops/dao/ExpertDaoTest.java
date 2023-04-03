package com.sysops.dao;

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
public class ExpertDaoTest {

    @Autowired
    private ExpertDao expertDao;

    @Test
    public void findMatchedExperts(){
        Expert expert1 = new Expert("111-111-1111", "Seattle", 1);
        Expert expert2 = new Expert("222-222-2222", "Seattle", 2);
        Expert expert3 = new Expert("333-333-3333", "Portland", 1);
        Expert expert4 = new Expert("444-444-4444", "Seattle", 1);
        expert4.setAvailable(false);
        Expert expert5 = new Expert("555-555-5555", "Portland", 2);
        expertDao.save(expert1);
        expertDao.save(expert2);
        expertDao.save(expert3);
        expertDao.save(expert4);
        expertDao.save(expert5);
        List<Expert> res = expertDao.findMatchedExperts(1, "Seattle");
        assertThat(res).containsOnly(expert1);
    }

    @Test
    public void findAvailableExperts() {
        // generate 5 experts
        Expert expert1 = new Expert("111-111-1111", "Seattle", 1);
        Expert expert2 = new Expert("222-222-2222", "Seattle", 2);
        Expert expert3 = new Expert("333-333-3333", "Portland", 1);
        Expert expert4 = new Expert("444-444-4444", "Seattle", 1);
        expert4.setAvailable(false);
        Expert expert5 = new Expert("555-555-5555", "Portland", 2);
        expert5.setAvailable(false);
        expertDao.save(expert1);
        expertDao.save(expert2);
        expertDao.save(expert3);
        expertDao.save(expert4);
        expertDao.save(expert5);
        List<Expert> res = expertDao.findAvailableExperts("Portland");
        assertThat(res).containsOnly(expert3);
    }
}
