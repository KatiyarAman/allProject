package com.db.multi.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.multi.csv.entity.PlaFeed;
import com.db.multi.response.PlaFeedService;
import com.db.multi.service.PlaFeedCacheService;

@RestController
@RequestMapping("/plaFeed/*")
public class PlaFeedController {

	private static final Logger logger = LogManager.getLogger(PlaFeedController.class);
	@Autowired
	private PlaFeedService plaFeedService;

//	@Autowired
//	private IPlaFeedApiCacheManager plaFeedcacheManager;
	
	@Autowired
	private PlaFeedCacheService cacheService;

	@GetMapping("list")
	public String getplaFeedList(@RequestParam Map<String, String> param) {

		logger.info("Size of param " + param.size());
		StringBuffer buffer = new StringBuffer();
		buffer.append(" WHERE ");
		int count = 0;
		for (java.util.Map.Entry<String, String> entrySet : param.entrySet()) {

			if(entrySet.getKey().equalsIgnoreCase("pageNumber")|| entrySet.getKey().equalsIgnoreCase("pageSize"))
				 continue;
			
			if (count==0) 
				buffer.append(entrySet.getKey()).append(" LIKE ").append(" '%").append(entrySet.getValue())
						.append("%'");

			 else if (count < param.size())
				buffer.append(" AND ").append(entrySet.getKey()).append(" LIKE ").append(" '%")
						.append(entrySet.getValue()).append("%'");
				
//			} else {
//				buffer.append(" AND ").append(entrySet.getKey()).append(" LIKE ").append(" '%")
//						.append(entrySet.getValue()).append("%' ");	
//			}

			count++;
        	}

		

		//pagination Start
		Integer pageNumber= Integer.valueOf( param.get("pageNumber"));
		Integer pageSize= Integer.valueOf(param.get("pageSize"));
		Integer offSet=(pageNumber-1)*pageSize;
		
		
		
		
		buffer.append(" LIMIT ").append(pageSize).append( " OFFSET ").append(offSet).append(" ; ");
		
		
		logger.info("count " + count + " " + param.size());
		
		
		return cacheService.getPlaFeed(buffer);
	}

	@GetMapping("getById")
	public PlaFeed getById(@RequestParam("id") Long Id) {
		return plaFeedService.getById(Id);
	}

}
