package com.eleiatech.categoryservice.service;


import com.eleiatech.categoryservice.repository.entity.Category;
import com.eleiatech.categoryservice.request.CategoryCreateRequest;
import com.eleiatech.categoryservice.request.CategoryUpdateRequest;

import java.util.List;

public interface ICategoryRepositoryService {

    Category createCategory(CategoryCreateRequest categoryCreateRequest);

    Category updateCategory(long categoryId, CategoryUpdateRequest categoryUpdateRequest);

    void deleteCategory(long categoryId);

    Category getCategory(long categoryId);

    List<Category> getCategoryWithCompany(long companyId);

    void deleteCategoryWithCompany(long companyId);



}
