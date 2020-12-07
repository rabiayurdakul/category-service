package com.eleiatech.categoryservice.repository;

import com.eleiatech.categoryservice.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getByCategoryIdAndDeletedFalse(long categoryId);

    List<Category> getByCompanyIdAndDeletedFalse(long companyId);
}
