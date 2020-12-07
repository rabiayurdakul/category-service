package com.eleiatech.categoryservice.service.impl;

import com.eleiatech.categoryservice.exception.exceptions.DataAlreadyDeletedException;
import com.eleiatech.categoryservice.exception.exceptions.DataNotFoundException;
import com.eleiatech.categoryservice.repository.CategoryRepository;
import com.eleiatech.categoryservice.repository.entity.Category;
import com.eleiatech.categoryservice.request.CategoryCreateRequest;
import com.eleiatech.categoryservice.request.CategoryUpdateRequest;
import com.eleiatech.categoryservice.service.ICategoryRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryRepositoryServiceImpl implements ICategoryRepositoryService {

   private final CategoryRepository categoryRepository;


    @Override
    public Category createCategory(CategoryCreateRequest categoryCreateRequest) {

        Category category= Category.builder()
                .categoryName(categoryCreateRequest.getCategoryName())
                .companyId(categoryCreateRequest.getCompanyId())
                .createdDate(new Date())
                .updatedDate(new Date())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(long categoryId, CategoryUpdateRequest categoryUpdateRequest) {

        Category category= getCategory(categoryId);
        category.setCategoryName(categoryUpdateRequest.getCategoryName());
        category.setCompanyId(categoryUpdateRequest.getCompanyId());
        category.setUpdatedDate(new Date());

        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long categoryId) {
       Category category;

       try{
           category = getCategory(categoryId);

       }catch (DataNotFoundException dataNotFoundException){
            throw new DataAlreadyDeletedException("Category "," Category Id: " + categoryId);
       }
       category.setDeleted(true);
       category.setUpdatedDate(new Date());
       categoryRepository.save(category);
    }

    @Override
    public Category getCategory(long categoryId) {
       Category category= categoryRepository.getByCategoryIdAndDeletedFalse(categoryId);

       if(Objects.isNull(category)){
           throw new DataNotFoundException("Category","Category Id: " + categoryId);
       }

        return category;
    }

    @Override
    public List<Category> getCategoryWithCompany(long companyId) {
        List<Category> categories = categoryRepository.getByCompanyIdAndDeletedFalse(companyId);

        if(categories.isEmpty()){
            throw new DataNotFoundException("Categories","");
        }
        return categories;
    }

    @Override
    public void deleteCategoryWithCompany(long companyId) {
        List<Category> categories;
        try{
            categories = getCategoryWithCompany(companyId);
        }catch (DataNotFoundException dataNotFoundException){
            throw new DataAlreadyDeletedException("Categories ","");
        }
        categories.forEach(args -> args.setDeleted(true));
        categories.forEach(args-> args.setUpdatedDate(new Date()));
        categoryRepository.saveAll(categories);


    }
}
