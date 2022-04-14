package com.ex.encrption.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ex.encrption.model.PhoneType;

import lombok.Data;

@Entity
@Table(name="phone")
@Data
public class Phone {
	 @Id
	  @GeneratedValue
	  private long id;
	  private String type;
	  private String number;
	  
	  @Column(name="employee_id")
	  private Long employeeId;
	  
	 
}
