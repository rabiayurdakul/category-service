package com.eleiatech.categoryservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryCreateRequest {

    private long companyId;

    private String categoryName;
}
