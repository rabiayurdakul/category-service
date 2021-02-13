package com.eleiatech.categoryservice.controller;

import com.eleiatech.categoryservice.repository.entity.SubCategory;
import com.eleiatech.categoryservice.request.SubCategoryCreateRequest;
import com.eleiatech.categoryservice.request.SubCategoryUpdateRequest;
import com.eleiatech.categoryservice.response.InternalApiResponse;
import com.eleiatech.categoryservice.response.SubCategoryResponse;
import com.eleiatech.categoryservice.service.ISubCategoryRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/sub-category")
public class SubCategoryController {

    private final ISubCategoryRepositoryService subCategoryRepositoryService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public InternalApiResponse<SubCategoryResponse> createSubCategory(@RequestBody SubCategoryCreateRequest subCategoryCreateRequest){
        SubCategory subCategory = subCategoryRepositoryService.createSubCategory(subCategoryCreateRequest);

        SubCategoryResponse subCategoryResponse = SubCategoryResponse.builder()
                .categoryId(subCategory.getCategory().getCategoryId())
                .subCategoryName(subCategory.getSubCategoryName())
                .companyId(subCategory.getCompanyId())
                .createdDate(subCategory.getCreatedDate())
                .updatedDate(subCategory.getUpdatedDate())
                .build();

        return InternalApiResponse.<SubCategoryResponse>builder()
                .hasError(false)
                .httpStatus(HttpStatus.CREATED)
                .payload(subCategoryResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update/{subCategoryId}")
    public InternalApiResponse<SubCategoryResponse> updateSubCategory(@PathVariable("subCategoryId") long subCategoryId, @RequestBody SubCategoryUpdateRequest subCategoryUpdateRequest){
        SubCategory subCategory = subCategoryRepositoryService.updateSubCategory(subCategoryId,subCategoryUpdateRequest);

        SubCategoryResponse subCategoryResponse = SubCategoryResponse.builder()
                .subCategoryId(subCategory.getSubCategoryId())
                .categoryId(subCategory.getCategory().getCategoryId())
                .subCategoryName(subCategory.getSubCategoryName())
                .companyId(subCategory.getCompanyId())
                .createdDate(subCategory.getCreatedDate())
                .updatedDate(subCategory.getUpdatedDate())
                .build();

        return InternalApiResponse.<SubCategoryResponse>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload(subCategoryResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{subCategoryId}")
    public InternalApiResponse<String> deleteSubCategory(@PathVariable("subCategoryId") long subCategoryId){
        subCategoryRepositoryService.deleteSubCategory(subCategoryId);

        return InternalApiResponse.<String>builder()
                .payload("Deleted")
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{categoryId}/category")
    public InternalApiResponse<String> deleteSubCategoryWithCategory(@PathVariable("categoryId") long categoryId){
        subCategoryRepositoryService.deleteSubCategoryWithCategory(categoryId);

        return InternalApiResponse.<String>builder()
                .payload("Deleted")
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete/{companyId}/company")
    public InternalApiResponse<String> deleteSubCategoryWithCompany(@PathVariable("companyId") long companyId){
        subCategoryRepositoryService.deleteSubCategoryWithCompany(companyId);

        return InternalApiResponse.<String>builder()
                .payload("Deleted")
                .httpStatus(HttpStatus.OK)
                .hasError(false)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{subCategoryId}")
    public InternalApiResponse<SubCategoryResponse> getSubCategory(@PathVariable("subCategoryId")  long subCategoryId){
        SubCategory subCategory = subCategoryRepositoryService.getSubCategory(subCategoryId);

        SubCategoryResponse subCategoryResponse = SubCategoryResponse.builder()
                .categoryId(subCategory.getCategory().getCategoryId())
                .subCategoryName(subCategory.getSubCategoryName())
                .companyId(subCategory.getCompanyId())
                .updatedDate(subCategory.getUpdatedDate())
                .createdDate(subCategory.getCreatedDate())
                .build();
        return InternalApiResponse.<SubCategoryResponse>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload(subCategoryResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{categoryId}/category")
    public InternalApiResponse<List<SubCategoryResponse>> getSubCategoryWithCategory(@PathVariable("categoryId") long categoryId){
        List<SubCategory> subCategoryList = subCategoryRepositoryService.getSubCategoryWithCategory(categoryId);

        List<SubCategoryResponse> subCategoryResponseList = subCategoryList.stream()
                .map(arg-> SubCategoryResponse.builder()
                .companyId(arg.getCompanyId())
                .categoryId(arg.getCategory().getCategoryId())
                .subCategoryName(arg.getSubCategoryName())
                .createdDate(arg.getCreatedDate())
                .updatedDate(arg.getUpdatedDate())
                .build())
                .collect(Collectors.toList());
        return InternalApiResponse.<List<SubCategoryResponse>>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload(subCategoryResponseList)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get/{companyId}/company")
    public InternalApiResponse<List<SubCategoryResponse>> getSubCategoryWithCompany(@PathVariable("companyId") long companyId){
        List<SubCategory> subCategoryList = subCategoryRepositoryService.getSubCategoryWithCompany(companyId);

        List<SubCategoryResponse> subCategoryResponseList = subCategoryList.stream()
                .map(arg-> SubCategoryResponse.builder()
                        .companyId(arg.getCompanyId())
                        .categoryId(arg.getCategory().getCategoryId())
                        .subCategoryName(arg.getSubCategoryName())
                        .createdDate(arg.getCreatedDate())
                        .updatedDate(arg.getUpdatedDate())
                        .build())
                .collect(Collectors.toList());
        return InternalApiResponse.<List<SubCategoryResponse>>builder()
                .hasError(false)
                .httpStatus(HttpStatus.OK)
                .payload(subCategoryResponseList)
                .build();
    }


}
