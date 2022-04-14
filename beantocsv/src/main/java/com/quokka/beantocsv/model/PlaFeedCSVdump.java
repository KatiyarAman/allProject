package com.quokka.beantocsv.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.quokka.beantocsv.entity.PlaFeed;

import lombok.Data;

@Data
public class PlaFeedCSVdump {
	@CsvBindByPosition(position = 0)
	@CsvBindByName(column = "id")
	private Long id;
	@CsvBindByPosition(position = 1)
	@CsvBindByName(column = "msn")
	private String msn;
	@CsvBindByPosition(position = 2)
	@CsvBindByName(column = "gtin")
	private String gtin;
	@CsvBindByPosition(position = 3)
	@CsvBindByName(column = "identifierExists")
	private String identifierExists;
	@CsvBindByPosition(position = 4)
	@CsvBindByName(column = "customLabel2")
	private String customLabel2;
	@CsvBindByPosition(position = 5)
	@CsvBindByName(column = "customLabel3")
	private String customLabel3;
	@CsvBindByPosition(position = 6)
	@CsvBindByName(column = "customLabel4")
	private String customLabel4;
	@CsvBindByPosition(position = 7)
	@CsvBindByName(column = "promotionId")
	private String promotionId;
	@CsvBindByPosition(position = 8)
	@CsvBindByName(column = "isGoogleActive")
	private String isGoogleActive;
	@CsvBindByPosition(position = 9)
	@CsvBindByName(column = "isFacebookActive")
	private String isFacebookActive;
	@CsvBindByPosition(position = 10)
	@CsvBindByName(column = "isCriteoActive")
	private String isCriteoActive;
	@CsvBindByPosition(position = 11)
	@CsvBindByName(column = "availability")
	private String availability;

	public static PlaFeedCSVdump bindPlaFeed(PlaFeed it) {
		PlaFeedCSVdump plaFeedCSVdump = new PlaFeedCSVdump();
		plaFeedCSVdump.setId(it.getId());
		plaFeedCSVdump.setMsn(it.getMsn());
		plaFeedCSVdump.setGtin(it.getGtin());
		plaFeedCSVdump.setIdentifierExists(it.getIdentifierExists());
		plaFeedCSVdump.setCustomLabel2(it.getCustomLabel2());
		plaFeedCSVdump.setCustomLabel3(it.getCustomLabel3());
		plaFeedCSVdump.setCustomLabel4(it.getCustomLabel4());
		plaFeedCSVdump.setPromotionId(it.getPromotionId());
		plaFeedCSVdump.setIsGoogleActive(it.getIsGoogleActive());
		plaFeedCSVdump.setIsFacebookActive(it.getIsFacebookActive());
		plaFeedCSVdump.setIsCriteoActive(it.getIsCriteoActive());
		plaFeedCSVdump.setAvailability(it.getAvailability());
		return plaFeedCSVdump;
	}
}
