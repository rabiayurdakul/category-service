package com.eleiatech.categoryservice.repository;

import com.eleiatech.categoryservice.repository.entity.Category;
import com.eleiatech.categoryservice.repository.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {

    SubCategory getBySubCategoryIdAndDeletedFalse(long subCategoryId);

    List<SubCategory> getByCompanyIdAndDeletedFalse(long companyId);

    List<SubCategory> getByCategoryCategoryIdAndDeletedFalse(long categoryId);


}
