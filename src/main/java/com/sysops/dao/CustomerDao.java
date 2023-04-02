package com.sysops.dao;

import com.sysops.entity.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * DAO for managing Customer entities.
 */
public interface CustomerDao extends CrudRepository<Customer, Long> {
}
