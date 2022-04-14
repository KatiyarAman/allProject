package com.db.multi.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.db.multi.csv.entity.PlaFeed;
import com.db.multi.csv.repo.PlaFeedRepo;
import com.db.multi.response.PlaFeedService;
import com.db.multi.utils.GenricSpecification;
import com.google.gson.Gson;

@Service
public class PlaFeedServiceImpl implements PlaFeedService {

	private static final Logger logger = LogManager.getLogger(PlaFeedServiceImpl.class);
	
	@Autowired
	private PlaFeedRepo plaFeedRepo;

	@Override
	public List<PlaFeed> getPlaFeedList(StringBuffer queryParams) {
		//logger.info("Search in all Plafeed  by params :{}", new Gson().toJson(param));

		//Specification<PlaFeed> specs = GenricSpecification.getSpecs(param);

	//	logger.info("Search in all plafeed by spec :{}", new Gson().toJson(specs));

		Pageable pageable = PageRequest.of(0, 10);
	//	logger.info(specs.toString());
		
		return plaFeedRepo.findbyColumn(queryParams);
	}

	@Override
	public PlaFeed getById(Long id) {
		return null;
	}

}
