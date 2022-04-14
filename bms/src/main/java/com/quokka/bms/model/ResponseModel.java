package com.quokka.bms.model;

public class ResponseModel<T> {
    private Boolean status;
    private Integer statusCode;
    private String statusDescription;
    private T data;
    private Integer errorCode;
    private String errorMesssage;

    public ResponseModel(T data) {
        this(true,data);
    }

    public ResponseModel(Boolean status, T data) {
        this(status,200,data);
    }

    public ResponseModel(Boolean status, Integer statusCode, T data) {
        this(status,statusCode,data,null,null);
    }
    public ResponseModel(Boolean status, Integer statusCode, T data, Integer errorCode, String errorMesssage) {
        this(status,statusCode,null,data,null,null);
    }

    public ResponseModel(Boolean status, Integer statusCode, String statusDescription, T data, Integer errorCode, String errorMesssage) {
        this.status = status;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.data = data;
        this.errorCode = errorCode;
        this.errorMesssage = errorMesssage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMesssage() {
        return errorMesssage;
    }

    public void setErrorMesssage(String errorMesssage) {
        this.errorMesssage = errorMesssage;
    }
}
