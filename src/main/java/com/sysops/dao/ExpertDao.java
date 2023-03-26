package com.sysops.dao;

import com.sysops.entity.Expert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpertDao extends CrudRepository<Expert, Long> {
    @Query("SELECT e FROM Expert e WHERE e.location = :location AND e.categoryId = :categoryId and e.isAvailable = true")
    List<Expert> findMatchedExperts(@Param("categoryId") int categoryId, @Param("location") String location);
}
