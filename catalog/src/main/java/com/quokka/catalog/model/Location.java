//package com.quokka.catalog.model;
//
//import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
//import org.springframework.data.cassandra.core.mapping.CassandraType;
//import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
//
//import org.springframework.data.cassandra.core.mapping.Column;
//import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
//import org.springframework.data.cassandra.core.mapping.Table;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//import java.util.List;
//
//@Table(value = "location_by_id")
//public class Location {
//    @PrimaryKeyColumn(name = "id_location",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
//    private String idLocation;
//    @Column("address_line")
//    @CassandraType(type = Name.TEXT)
//    private String addressLine;
//    @Column("optional_address_line")
//    @CassandraType(type = Name.TEXT)
//    private String optionalAddressLine;
//    @Column("zip_code")
//    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
//    private List<String> zipCode;
//    @Column("city_name")
//    @CassandraType(type = Name.TEXT)
//    private String cityName;
//    @Column("state_name")
//    @CassandraType(type = Name.TEXT)
//    private String stateName;
//    @Column("coutry_name")
//    @CassandraType(type = Name.TEXT)
//    private String countryName;
//    @Column( "created_on")
//    @CassandraType(type = Name.TIMESTAMP)
//    private Date createdOn;
//
//    @Column( "updated_on")
//    @CassandraType(type = Name.TIMESTAMP)
//    private Date updatedOn;
//    @Column( "is_active")
//    @CassandraType(type = Name.BLOB)
//    private Boolean isActive;
//
//    public Location() {
//        super();
//        this.idLocation=getLocationId();
//        this.isActive=false;
//    }
//
//    public Location(String idLocation, String addressLine, String optionalAddressLine, List<String> zipCode, String cityName, String stateName, String countryName) {
//        this.idLocation = getLocationId();
//        this.addressLine = addressLine;
//        this.optionalAddressLine = optionalAddressLine;
//        this.zipCode = zipCode;
//        this.cityName = cityName;
//        this.stateName = stateName;
//        this.countryName = countryName;
//        this.createdOn=new Date();
//        this.updatedOn=new Date();
//        this.isActive=false;
//    }
//
//    public String getLocationId(){
//        LocalDateTime localDateTime= LocalDateTime.now();
//        localDateTime.plus(5, ChronoUnit.MILLIS);
//        return "LOC"+Date.from(localDateTime.toInstant(ZoneOffset.UTC)).getTime();
//    }
//    public String getIdLocation() {
//        return idLocation;
//    }
//
//    public void setIdLocation(String idLocation) {
//        this.idLocation = idLocation;
//    }
//
//    public String getAddressLine() {
//        return addressLine;
//    }
//
//    public void setAddressLine(String addressLine) {
//        this.addressLine = addressLine;
//    }
//
//    public String getOptionalAddressLine() {
//        return optionalAddressLine;
//    }
//
//    public void setOptionalAddressLine(String optionalAddressLine) {
//        this.optionalAddressLine = optionalAddressLine;
//    }
//
//    public List<String> getZipCode() {
//        return zipCode;
//    }
//
//    public void setZipCode(List<String> zipCode) {
//        this.zipCode = zipCode;
//    }
//
//    public String getCityName() {
//        return cityName;
//    }
//
//    public void setCityName(String cityName) {
//        this.cityName = cityName;
//    }
//
//    public String getStateName() {
//        return stateName;
//    }
//
//    public void setStateName(String stateName) {
//        this.stateName = stateName;
//    }
//
//    public String getCountryName() {
//        return countryName;
//    }
//
//    public void setCountryName(String countryName) {
//        this.countryName = countryName;
//    }
//
//    public Date getCreatedOn() {
//        return createdOn;
//    }
//
//    public void setCreatedOn(Date createdOn) {
//        this.createdOn = createdOn;
//    }
//
//    public Date getUpdatedOn() {
//        return updatedOn;
//    }
//
//    public void setUpdatedOn(Date updatedOn) {
//        this.updatedOn = updatedOn;
//    }
//}
