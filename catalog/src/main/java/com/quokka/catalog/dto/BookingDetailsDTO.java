package com.quokka.catalog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

public class BookingDetailsDTO {
    private Long id;

    private String productId;

    private String categoryId;

    private Boolean status;
    private Timestamp startingAt;
    private String endingAt;

    private Timestamp createdAt= new Timestamp(new Date().getTime());

    private Timestamp modifiedAt= new Timestamp(new Date().getTime());

    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getStartingAt() {
        return startingAt;
    }

    public void setStartingAt(Timestamp startingAt) {
        this.startingAt = startingAt;
    }

    public String getEndingAt() {
        return endingAt;
    }

    public void setEndingAt(String endingAt) {
        this.endingAt = endingAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
