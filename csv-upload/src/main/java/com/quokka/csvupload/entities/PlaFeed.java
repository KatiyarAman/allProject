package com.quokka.csvupload.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opencsv.bean.CsvBindByName;

import lombok.Data;

@Data
@Entity
@Table(name="pla_feed")
public class PlaFeed {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@CsvBindByName(column = "msn")
	private String msn;
	@CsvBindByName(column = "gtin")
	private String gtin;
	@CsvBindByName(column = "identifierExists")
	private String identifierExists;
	@CsvBindByName(column = "customLabel2")
	private String customLabel2;
	@CsvBindByName(column = "customeLabel3")
	private String customLabel3;
	@CsvBindByName(column = "customLabel4")
	private String customLabel4;
	@CsvBindByName(column = "promotionId")
	private String promotionId;
	@CsvBindByName(column = "isGoogleActive")
	private String isGoogleActive;
	@CsvBindByName(column = "isFacebookActive")
	private String isFacebookActive;
	@CsvBindByName(column = "isCriteoActive")
	private String isCriteoActive;
	@CsvBindByName(column = "s")
	private String availability;
}
