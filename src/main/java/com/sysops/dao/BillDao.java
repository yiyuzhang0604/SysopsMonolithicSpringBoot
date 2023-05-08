package com.sysops.dao;

import com.sysops.entity.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillDao extends CrudRepository<Bill, Long> {
}
