package com.db.multi.response;

public class MultResponse <T>{
    private boolean status;
    private String statusDescription;
    private T data;
    private String errorMessage;
    private int errorCode;

    MultResponse(T data){
        this(true,data);
    }

    public MultResponse(boolean status, T data) {
       this(status,data,null);
    }

    public MultResponse(boolean status, T data, String statusDescription) {
        this.status=status;
        this.data=data;
        this.statusDescription=statusDescription;
    }
    public MultResponse(boolean status, T data, String statusDescription,String errorMessage,int errorCode)
    {
        this.status=status;
        this.data=data;
        this.statusDescription=statusDescription;
        this.errorMessage=errorMessage;
        this.errorCode=errorCode;
    }
    MultResponse(){}
}
