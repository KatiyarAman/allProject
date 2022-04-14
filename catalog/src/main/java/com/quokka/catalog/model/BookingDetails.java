package com.quokka.catalog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Date;

@Table(value = "booking_details")
public class BookingDetails {
@PrimaryKeyColumn(name = "id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private Long id;
    @Column( "product_id")
    @CassandraType(type =Name.TEXT)
    private String productId;
    @Column( "category_id")
    @CassandraType(type =Name.TEXT)
    private String categoryId;
    @Column( "status")
    @CassandraType(type =Name.BOOLEAN)
    private Boolean status;
    @Column( "starting_at")
    @CassandraType(type =Name.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startingAt;
    @Column("ending_at")
    @CassandraType(type =Name.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endingAt;
    @Column( "created_at")
    @CassandraType(type =Name.TIMESTAMP)
    private Timestamp createdAt= new Timestamp(new Date().getTime());
    @Column( "modified_at")
    @LastModifiedDate
    @CassandraType(type =Name.TIMESTAMP)
    private Timestamp modifiedAt= new Timestamp(new Date().getTime());
    @Column( "created_by")
    @CassandraType(type =Name.TEXT)
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
