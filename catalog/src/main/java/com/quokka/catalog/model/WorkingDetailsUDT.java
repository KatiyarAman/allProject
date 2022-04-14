package com.quokka.catalog.model;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;
@UserDefinedType
public class WorkingDetailsUDT {
    @Column("zipCode")
    @CassandraType(type = Name.LIST,typeArguments = Name.TEXT)
    private List<String> zipCode;
    @Column("city_name")
    @CassandraType(type = Name.TEXT)
    private String cityName;
    @Column("address_line")
    @CassandraType(type = Name.TEXT)
    private String addressLine;
    @Column("optional_address_line")
    @CassandraType(type = Name.TEXT)
    private String optionalAddressLine;
    @Column("state_name")
    @CassandraType(type = Name.TEXT)
    private String stateName;

    public List<String> getZipCode() {
        return zipCode;
    }

    public void setZipCode(List<String> zipCode) {
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getOptionalAddressLine() {
        return optionalAddressLine;
    }

    public void setOptionalAddressLine(String optionalAddressLine) {
        this.optionalAddressLine = optionalAddressLine;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
