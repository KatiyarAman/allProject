package com.quokka.beantocsv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name="pla_feed")
public class PlaFeed {
//provide column name that are present in the table
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String msn;
	private String gtin;
	private String identifierExists;
	private String customLabel2;
	private String customLabel3;
	private String customLabel4;
	private String promotionId;
	private String isGoogleActive;
	private String isFacebookActive;
	private String isCriteoActive;
	private String availability;
}
