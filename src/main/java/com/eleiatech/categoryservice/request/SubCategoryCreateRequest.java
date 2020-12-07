package com.eleiatech.categoryservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryCreateRequest {

    private long companyId;

    private long categoryId;

    private String subCategoryName;
}
