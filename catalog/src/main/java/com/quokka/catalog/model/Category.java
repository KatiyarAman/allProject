package com.quokka.catalog.model;

import com.datastax.oss.protocol.internal.ProtocolConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Table(value = "category_by_id")
public class Category {
    @Id
    @PrimaryKeyColumn(name = "category_code",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String categoryCode;

    @Column("category_name")
    @CassandraType(type = Name.TEXT)
    private String categoryName;

    @Column("parents_hierarchy")
    private Map<Integer, HierarchyUDT> parentHierarchy;

    @Column("parent_list")
    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
    private Set<String> parentList;

    @Column( "child_list")
    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
    private List<String> childList;

    @Column("category_desc")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String categoryDesc;

    @Column(value = "category_short_desc")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String categoryShortDesc;

    @Column(value = "other_details")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String otherDetails;

    @Column(value = "created_on")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date createdOn;

    @Column(value = "updated_on")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Date updatedOn;

    @Column(value = "deleted_flag")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean deletedFlag;

    @Column(value = "is_active")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean isActive;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Map<Integer, HierarchyUDT> getParentHierarchy() {
        return parentHierarchy;
    }

    public void setParentHierarchy(Map<Integer, HierarchyUDT> parentHierarchy) {
        this.parentHierarchy = parentHierarchy;
    }

    public Set<String> getParentList() {
        return parentList;
    }

    public void setParentList(Set<String> parentList) {
        this.parentList = parentList;
    }

    public List<String> getChildList() {
        return childList;
    }

    public void setChildList(List<String> childList) {
        this.childList = childList;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getCategoryShortDesc() {
        return categoryShortDesc;
    }

    public void setCategoryShortDesc(String categoryShortDesc) {
        this.categoryShortDesc = categoryShortDesc;
    }

    public String getOtherDetails() {
        return otherDetails;
    }

    public void setOtherDetails(String otherDetails) {
        this.otherDetails = otherDetails;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
