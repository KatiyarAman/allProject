//package com.db.multi.service.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import com.db.multi.csv.entity.PlaFeed;
//import com.db.multi.response.PlaFeedService;
//import com.db.multi.service.IPlaFeedApiCacheManager;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//@Service
//public class PlaFeedApiCacheManager implements IPlaFeedApiCacheManager {
//	private static final Logger logger=LogManager.getLogger(PlaFeedApiCacheManager.class);
//	public static final String GET_BESTSELLERS_BY_CATEGORY_ID = "getBestSellersByCategoryId";	
//	@Autowired
//	private PlaFeedService plaFeedService;
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@Override
//	@Cacheable(value="plaFeed",key="#root.target.GET_BESTSELLERS_BY_CATEGORY_ID +#params.entrySet()",unless="#plaFeed == null")
//	public String getPlaFeed(Map<String, Object> params,StringBuffer queryParams) {
//		//logger.info("Getting plaFeed by params : {}",new Gson().toJson(params));
//		String plaFeed = null;
//		
//		try {
//			List<PlaFeed> plaFeeds=plaFeedService.getPlaFeedList(params,queryParams);
//			plaFeed	=objectMapper.writeValueAsString(plaFeeds);
//		} catch (JsonProcessingException e) {
//			logger.error("error occured while processing the object to string : {}",new Gson().toJson(plaFeed));
//			e.printStackTrace();
//		}
//			
//		
//		return plaFeed ;
//	}
//
//}
