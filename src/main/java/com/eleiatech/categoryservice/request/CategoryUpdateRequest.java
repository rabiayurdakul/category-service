package com.eleiatech.categoryservice.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryUpdateRequest {

    private long categoryId;

    private long companyId;

    private String categoryName;
}
