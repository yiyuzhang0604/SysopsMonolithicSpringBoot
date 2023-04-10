package com.sysops.dao;

import com.sysops.entity.Expert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * DAO for managing Expert entities.
 */
public interface ExpertDao extends CrudRepository<Expert, Long> {
    /**
     * Find matched experts based on categoryId, location and availability.
     *
     * @param categoryId categoryId of problem
     * @param location   location of the customer
     * @return a list of experts that meet the requirements
     */
    @Query("SELECT e FROM Expert e WHERE e.location = :location AND :categoryId IN elements(e.categories) AND e.isAvailable = true")
    List<Expert> findMatchedExperts(@Param("categoryId") Integer categoryId, @Param("location") String location);

    /**
     * Find expert by location and isAvailable
     * @param location
     * @return list of experts that meet the requirements
     */
    @Query("SELECT e FROM Expert e WHERE e.location = :location AND e.isAvailable = true")
    List<Expert> findAvailableExperts(@Param("location") String location);
}
