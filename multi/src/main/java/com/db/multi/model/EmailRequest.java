package com.db.multi.model;

import lombok.Data;
import org.json.simple.JSONObject;
@Data
public class EmailRequest {
    private String userId;
    private int ttl;
    private JSONObject token;
    private String userEmail;

   public EmailRequest(String userId,int ttl,JSONObject token,String userEmail){
        this.userId=userId;
        this.ttl=ttl;
        this.token=token;
        this.userEmail=userEmail;
    }
    EmailRequest(){}
}
