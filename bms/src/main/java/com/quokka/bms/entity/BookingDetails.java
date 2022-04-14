package com.quokka.bms.entity;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Table(name = "new_table")
@Entity
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "status")
    private Boolean status;
    @Column(name = "starting_at",nullable = false)
    private Timestamp startingAt;
    @Column(name = "ending_at",nullable = false)
    private Timestamp endingAt;
    @Column(name = "created_at",nullable = false,updatable = false)
    private Timestamp createdAt= new Timestamp(new Date().getTime());
    @Column(name = "modified_at",nullable = false)
    @LastModifiedDate
    private Timestamp modifiedAt= new Timestamp(new Date().getTime());
    @Column(name = "created_by")
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

    public Timestamp getEndingAt() {
        return endingAt;
    }

    public void setEndingAt(Timestamp endingAt) {
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
