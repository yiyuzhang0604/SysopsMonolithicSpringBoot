package com.sysops.dao;

import com.sysops.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * DAO for managing Customer entities.
 */
public interface CustomerDao extends CrudRepository<Customer, Long> {
/**
     * Find customer by customerId.
     *
     * @param customerId customerId of customer
     * @return a customer that meet the requirements
     */
    @Query("SELECT c FROM Customer c WHERE c.customerId = :customerId")
    Customer findCustomerByCustomerId(@Param("customerId") Long customerId);
}
