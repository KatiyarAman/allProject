package com.quokka.catalog.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Table(value = "product_by_id")
public class Product {

    @PrimaryKeyColumn(name = "id_product",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String idProduct;

    @Column( "category_code")
    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
    private List<String> categoryCode;

    @Column( "product_name")
    @CassandraType(type = Name.TEXT)
    private String productName;

    @Column("product_type")
    @CassandraType(type = Name.TEXT)
    private String productType;

    @Column("enterprise_type")
    @CassandraType(type = Name.TEXT)
    private String entrerpriseType;

    @Column("short_description")
    @CassandraType(type = Name.TEXT)
    private String shortDescription;

    @Column("adhar_number")
    @CassandraType(type = Name.INT)
    private Integer adharNumber;

    @Column("zip_code")
    @CassandraType(type = Name.INT)
    private Integer zipCode;

    @Column("contact_number")
    @CassandraType(type = Name.BIGINT)
    private Long contactNumber;

    @Column("optional_number")
    @CassandraType(type = Name.BIGINT)
    private Long optionalNumber;

    @Column( "working_details")
    private WorkingDetailsUDT workingDetails;

    @Column( "shipment_details")
    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
    private List<String> shipmentDetails;

    @Column("city_available_in")
    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
    private List<String> cityAvailableIn;

    @Column("rating")
    @CassandraType(type = Name.TEXT)
    private String rating;

    @Column( "mrp")
    @CassandraType(type = Name.MAP,typeArguments = {Name.TEXT,Name.DOUBLE})
    private Map<String, Double> mrpByCategory;

    @Column("verified_flag")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean verifiedFlag;

    @Column("deleted_flag")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean deletedFlag;

    @Column( "available_for_order")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean availableForOrder;

    @Column( "active")
    @CassandraType(type = Name.BOOLEAN)
    private Boolean active;

    @Column("last_updated_by")
    @CassandraType(type = Name.TEXT)
    private String lastUpdatedBy;

    @Column( "created_on")
    @CassandraType(type = Name.TIMESTAMP)
    private Date createdOn;

    @Column( "updated_on")
    @CassandraType(type = Name.TIMESTAMP)
    private Date updatedOn;

    public Product() {
        super();
        this.idProduct=getProductId();
        this.deletedFlag=false;
    }

    public String getProductId(){
        LocalDateTime localDateTime= LocalDateTime.now();
        localDateTime.plus(5, ChronoUnit.MILLIS);
        return "PID"+Date.from(localDateTime.toInstant(ZoneOffset.UTC)).getTime();
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public List<String> getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(List<String> categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getEntrerpriseType() {
        return entrerpriseType;
    }

    public void setEntrerpriseType(String entrerpriseType) {
        this.entrerpriseType = entrerpriseType;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public WorkingDetailsUDT getWorkingDetails() {
        return workingDetails;
    }

    public void setWorkingDetails(WorkingDetailsUDT workingDetails) {
        this.workingDetails = workingDetails;
    }

    public List<String> getShipmentDetails() {
        return shipmentDetails;
    }

    public void setShipmentDetails(List<String> shipmentDetails) {
        this.shipmentDetails = shipmentDetails;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Map<String, Double> getMrpByCategory() {
        return mrpByCategory;
    }

    public void setMrpByCategory(Map<String, Double> mrpByCategory) {
        this.mrpByCategory = mrpByCategory;
    }

    public Boolean getVerifiedFlag() {
        return verifiedFlag;
    }

    public void setVerifiedFlag(Boolean verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }

    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public Boolean getAvailableForOrder() {
        return availableForOrder;
    }

    public void setAvailableForOrder(Boolean availableForOrder) {
        this.availableForOrder = availableForOrder;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
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

    public Integer getAdharNumber() {
        return adharNumber;
    }

    public void setAdharNumber(Integer adharNumber) {
        this.adharNumber = adharNumber;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public Long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Long getOptionalNumber() {
        return optionalNumber;
    }

    public void setOptionalNumber(Long optionalNumber) {
        this.optionalNumber = optionalNumber;
    }

    public List<String> getCityAvailableIn() {
        return cityAvailableIn;
    }

    public void setCityAvailableIn(List<String> cityAvailableIn) {
        this.cityAvailableIn = cityAvailableIn;
    }
}
