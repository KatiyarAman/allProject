package com.ex.encrption.entites;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="employee_demo")
public class Employee {
	 @Id
	  @GeneratedValue
	  private long id;
	  private String name;
	  
	  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	  @JoinColumn(name="employee_id")
	  private List<Phone> phones;
}
