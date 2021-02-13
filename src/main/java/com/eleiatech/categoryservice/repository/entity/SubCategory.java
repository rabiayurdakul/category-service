package com.eleiatech.categoryservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@Table(name = "sub_category", schema="stock_management")
public class SubCategory {

    @Column(name = "sub_category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subCategoryId;

    @JoinColumn(name = "category_id",nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Category category;

    @Column(name = "company_id")
    private long companyId;

    @Column(name = "sub_category_name")
    private String subCategoryName;

    @Builder.Default
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate = new Date();

    @Builder.Default
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate = new Date();

    @Column(name = "is_deleted")
    private boolean deleted;
}
