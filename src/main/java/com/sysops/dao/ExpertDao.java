package com.sysops.dao;

import com.sysops.entity.Expert;
import org.springframework.data.repository.CrudRepository;

public interface ExpertDao extends CrudRepository<Expert, Long> {
}
