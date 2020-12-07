package com.eleiatech.categoryservice.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {

    private long categoryId;

    private long companyId;

    private String categoryName;

    private Date createdDate;

    private Date updatedDate;
}
