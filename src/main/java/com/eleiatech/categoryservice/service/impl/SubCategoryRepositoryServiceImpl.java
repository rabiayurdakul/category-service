package com.eleiatech.categoryservice.service.impl;

import com.eleiatech.categoryservice.exception.exceptions.DataAlreadyDeletedException;
import com.eleiatech.categoryservice.exception.exceptions.DataNotFoundException;
import com.eleiatech.categoryservice.repository.CategoryRepository;
import com.eleiatech.categoryservice.repository.SubCategoryRepository;
import com.eleiatech.categoryservice.repository.entity.Category;
import com.eleiatech.categoryservice.repository.entity.SubCategory;
import com.eleiatech.categoryservice.request.SubCategoryCreateRequest;
import com.eleiatech.categoryservice.request.SubCategoryUpdateRequest;
import com.eleiatech.categoryservice.service.ISubCategoryRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SubCategoryRepositoryServiceImpl implements ISubCategoryRepositoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public SubCategory createSubCategory(SubCategoryCreateRequest subCategoryCreateRequest) {
        Category category = categoryRepository.getByCategoryIdAndDeletedFalse(subCategoryCreateRequest.getCategoryId());
        SubCategory subCategory = SubCategory.builder()
                .subCategoryName(subCategoryCreateRequest.getSubCategoryName())
                .category(category)
                .createdDate(new Date())
                .companyId(subCategoryCreateRequest.getCompanyId())
                .build();
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(long subCategoryId, SubCategoryUpdateRequest subCategoryUpdateRequest) {
        Category category = categoryRepository.getByCategoryIdAndDeletedFalse(subCategoryUpdateRequest.getCategoryId());
        SubCategory subCategory = SubCategory.builder()
                .subCategoryId(subCategoryId)
                .subCategoryName(subCategoryUpdateRequest.getSubCategoryName())
                .category(category)
                .updatedDate(new Date())
                .companyId(subCategoryUpdateRequest.getCompanyId())
                .build();
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public void deleteSubCategory(long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.getBySubCategoryIdAndDeletedFalse(subCategoryId);
        subCategory.setDeleted(true);
        subCategory.setUpdatedDate(new Date());
        subCategoryRepository.save(subCategory);
    }

    @Override
    public void deleteSubCategoryWithCategory(long categoryId) {
        List<SubCategory> subCategoryList;

        try{
            subCategoryList = getSubCategoryWithCategory(categoryId);
        }catch (DataNotFoundException dataNotFoundException){
            throw new DataAlreadyDeletedException("Sub Categories ", "");
        }
        subCategoryList.forEach(arg->arg.setUpdatedDate(new Date()));
        subCategoryList.forEach(arg->arg.setDeleted(true));
        subCategoryRepository.saveAll(subCategoryList);
    }

    @Override
    public void deleteSubCategoryWithCompany(long companyId) {
        List<SubCategory> subCategoryList;

        try{
            subCategoryList = getSubCategoryWithCompany(companyId);
        }catch (DataNotFoundException dataNotFoundException){
            throw new DataAlreadyDeletedException("Sub Categories ", "");
        }
        subCategoryList.forEach(arg->arg.setUpdatedDate(new Date()));
        subCategoryList.forEach(arg->arg.setDeleted(true));
        subCategoryRepository.saveAll(subCategoryList);
    }

    @Override
    public SubCategory getSubCategory(long subCategoryId) {
        SubCategory subCategory = subCategoryRepository.getBySubCategoryIdAndDeletedFalse(subCategoryId);
        if(Objects.isNull(subCategory)){
            throw new DataNotFoundException("Sub Category","Sub Category Id: " + subCategoryId);
        }
        return subCategory;
    }

    @Override
    public List<SubCategory> getSubCategoryWithCategory(long categoryId) {
        List<SubCategory> subCategoryList = subCategoryRepository.getByCategoryCategoryIdAndDeletedFalse(categoryId);
        if(subCategoryList.isEmpty()){
            throw new DataNotFoundException("Sub Categories","");
        }
        return subCategoryList;
    }

    @Override
    public List<SubCategory> getSubCategoryWithCompany(long companyId) {
        List<SubCategory> subCategoryList = subCategoryRepository.getByCompanyIdAndDeletedFalse(companyId);
        if(subCategoryList.isEmpty()){
            throw new DataNotFoundException("Sub Categories","");
        }
        return subCategoryList;
    }
}
