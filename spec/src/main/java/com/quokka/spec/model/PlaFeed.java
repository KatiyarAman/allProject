package com.quokka.spec.model;

import lombok.Data;

@Data
public class PlaFeed {
	private Long id;
	private String msn;
	private String gtin;
//@Column(name = "identifier_exists")
	private String identifierExists;
//@Column(name = "custom_label2")
	private String customLabel2;
//@Column(name = "custom_label3")
	private String customLabel3;
//@Column(name = "custom_label4")
	private String customLabel4;
//@Column(name = "promotion_id")
	private String promotionId;
//@Column(name = "is_google_active")
	private String isGoogleActive;
//@Column(name = "is_facebook_active")
	private String isFacebookActive;
//@Column(name = "is_criteo_active")
	private String isCriteoActive;
//@Column(name = "availability")
	private String availability;
public PlaFeed(Long id, String msn, String gtin, String identifierExists, String customLabel2, String customLabel3,
		String customLabel4, String promotionId, String isGoogleActive, String isFacebookActive, String isCriteoActive,
		String availability) {
	super();
	this.id = id;
	this.msn = msn;
	this.gtin = gtin;
	this.identifierExists = identifierExists;
	this.customLabel2 = customLabel2;
	this.customLabel3 = customLabel3;
	this.customLabel4 = customLabel4;
	this.promotionId = promotionId;
	this.isGoogleActive = isGoogleActive;
	this.isFacebookActive = isFacebookActive;
	this.isCriteoActive = isCriteoActive;
	this.availability = availability;
}
public PlaFeed() {
	super();
}

	
}
