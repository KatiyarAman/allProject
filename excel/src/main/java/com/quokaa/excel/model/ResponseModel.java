package com.quokaa.excel.model;

public class ResponseModel <T>{
    private String statusDescription;
    private Boolean status;
    private T data;
    private Integer statusCode;

    public ResponseModel(String statusDescription, Boolean status,T data,Integer statusCode) {
        this.statusDescription=statusDescription;
        this.status = status;
        this.data = data;
        this.statusCode=statusCode;
    }
    public ResponseModel(Boolean status,T data,String statusDescription){
        this.status=status;
        this.data=data;
        this.statusDescription=statusDescription;
    }
    public ResponseModel(){
        this(null);
    }
    public ResponseModel(T data){
        this(true,data);
    }
    public  ResponseModel(Boolean status,T data){
        this.status=status;
        this.data=data;
    }
}
