package com.quokka.searching.service;

import java.util.List;
import java.util.Map;

import com.quokka.searching.entity.PlaFeed;

public interface PlafeedService {
List<PlaFeed> search(Integer pageNumber,Integer pageSize,String msn,String gtin,String identifierExists,String customLabel2,
		String customLabel3,String customLabel4,String promotionId,String isGoogleActive,
		String isFacebookActive,String isCriteoActive,String availability);
	/*
	 * msn; gtin; identifierExists; customLabel2; customLabel3; customLabel4;
	 * promotionId; isGoogleActive; isFacebookActive; isCriteoActive; availability;
	 */

String searchWithMap(Integer pageNumber, Integer pageSize, Map<String, Object> map);
	Boolean evictPlaFeedListAll();
}
