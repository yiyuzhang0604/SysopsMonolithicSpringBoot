package com.sysops.dao;

import com.sysops.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    // get transactions by customer id
    List<Transaction> findByCustomerId(Long customerId);

    // get transactions by merchant id
    List<Transaction> findByExpertId(Long expertId);

    // get transactions by order id
    List<Transaction> findByOrderId(Long orderId);
}
