package com.eleiatech.categoryservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubCategoryUpdateRequest {

    private long subCategoryId;

    private long categoryId;

    private long companyId;

    private String subCategoryName;
}
