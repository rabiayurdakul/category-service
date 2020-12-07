package com.eleiatech.categoryservice.service;

import com.eleiatech.categoryservice.repository.entity.Category;
import com.eleiatech.categoryservice.repository.entity.SubCategory;
import com.eleiatech.categoryservice.request.CategoryCreateRequest;
import com.eleiatech.categoryservice.request.CategoryUpdateRequest;
import com.eleiatech.categoryservice.request.SubCategoryCreateRequest;
import com.eleiatech.categoryservice.request.SubCategoryUpdateRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubCategoryRepositoryService {

    SubCategory createSubCategory(SubCategoryCreateRequest subCategoryCreateRequest);

    SubCategory updateSubCategory(long subCategoryId, SubCategoryUpdateRequest subCategoryUpdateRequest);

    void deleteSubCategory(long subCategoryId);

    void deleteSubCategoryWithCategory(long categoryId);

    void deleteSubCategoryWithCompany(long companyId);

    SubCategory getSubCategory(long subCategoryId);

    List<SubCategory> getSubCategoryWithCategory(long categoryId);

    List<SubCategory> getSubCategoryWithCompany(long companyId);


}
