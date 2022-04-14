package com.db.multi.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Document(collection = "user_details")
public class UserDetails {
	@Id
	  @Field("_id")
	  private String id;
    @Field(value = "user_name")
    private String userName;
    @Field(value = "detail")
    private String details;
}
