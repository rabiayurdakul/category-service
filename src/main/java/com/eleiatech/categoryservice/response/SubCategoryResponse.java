package com.eleiatech.categoryservice.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class SubCategoryResponse {

    private long categoryId;

    private long companyId;

    private String subCategoryName;

    private Date createdDate;

    private Date updatedDate;
}
