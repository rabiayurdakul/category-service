package com.eleiatech.categoryservice.controller;


import com.eleiatech.categoryservice.repository.entity.Category;
import com.eleiatech.categoryservice.request.CategoryCreateRequest;
import com.eleiatech.categoryservice.request.CategoryUpdateRequest;
import com.eleiatech.categoryservice.response.CategoryResponse;
import com.eleiatech.categoryservice.response.InternalApiResponse;
import com.eleiatech.categoryservice.service.ICategoryRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ICategoryRepositoryService categoryRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public InternalApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreateRequest categoryCreateRequest) {
        Category category = categoryRepositoryService.createCategory(categoryCreateRequest);
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .companyId(category.getCompanyId())
                .categoryName(category.getCategoryName())
                .createdDate(category.getCreatedDate())
                .updatedDate(category.getUpdatedDate())
                .build();
        return InternalApiResponse.<CategoryResponse>builder()
                .hasError(false)
                .httpStatus(HttpStatus.CREATED)
                .payload(categoryResponse)
                .build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{categoryId}")
    public InternalApiResponse<CategoryResponse> updateCategory(@PathVariable("categoryId") long categoryId, @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        Category category = categoryRepositoryService.updateCategory(categoryId, categoryUpdateRequest);
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .companyId(category.getCompanyId())
                .categoryName(category.getCategoryName())
                .createdDate(category.getCreatedDate())
                .updatedDate(category.getUpdatedDate())
                .build();
        return InternalApiResponse.<CategoryResponse>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload(categoryResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{categoryId}")
    public InternalApiResponse<CategoryResponse> getCategory(@PathVariable("categoryId") long categoryId) {
        Category category = categoryRepositoryService.getCategory(categoryId);
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .companyId(category.getCompanyId())
                .categoryName(category.getCategoryName())
                .createdDate(category.getCreatedDate())
                .updatedDate(category.getUpdatedDate())
                .build();
        return InternalApiResponse.<CategoryResponse>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload(categoryResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{companyId}/company")
    public InternalApiResponse<List<CategoryResponse>> getCategoryWithCompany(@PathVariable("companyId") long companyId) {
        List<Category> categories = categoryRepositoryService.getCategoryWithCompany(companyId);
        List<CategoryResponse> categoryResponses = categories.stream()
                .map(arg -> new CategoryResponse(arg.getCategoryId(), arg.getCompanyId() ,arg.getCategoryName(), arg.getCreatedDate(), arg.getUpdatedDate()))
                .collect(Collectors.toList());
        return InternalApiResponse.<List<CategoryResponse>>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload(categoryResponses)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{categoryId}")
    public InternalApiResponse<String> deleteCategory(@PathVariable("categoryId") long categoryId) {
        categoryRepositoryService.deleteCategory(categoryId);
        return InternalApiResponse.<String>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload("Deleted")
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{companyId}/company")
    public InternalApiResponse<String> deleteCategoryWithCompany(@PathVariable("companyId") long companyId) {
        categoryRepositoryService.deleteCategoryWithCompany(companyId);
        return InternalApiResponse.<String>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload("Deleted")
                .build();
    }


}
