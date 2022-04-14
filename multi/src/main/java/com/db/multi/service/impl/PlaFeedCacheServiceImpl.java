package com.db.multi.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.db.multi.response.PlaFeedService;
import com.db.multi.service.PlaFeedCacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class PlaFeedCacheServiceImpl implements PlaFeedCacheService {
	
	public static final String BEST_SELLLER="BestSeller";
	
	@Autowired
	private PlaFeedService plaFeedService;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	@Cacheable(value="plaFeed",key="#root.target.BEST_SELLLER + #queryPAram",unless="#plaFeed==null")
	public String getPlaFeed(StringBuffer queryPAram) {
		
		String plaFeed;
		
		try {
			plaFeed=objectMapper.writeValueAsString(plaFeedService.getPlaFeedList( queryPAram));
		} catch (JsonProcessingException e) {
			return null;
			
		}
		
		return plaFeed;
	}

}
