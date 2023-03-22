package com.sysops.dao;

import com.sysops.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDao extends CrudRepository<Category, Long> {
}
