package com.quokka.searching.service.impl;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.quokka.searching.entity.PlaFeed;
import com.quokka.searching.repository.PlaFeedRepository;
import com.quokka.searching.service.PlafeedService;
import com.quokka.searching.specs.GenricSpecification;
import com.quokka.searching.specs.PlaFeedSpecs;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class PlafeedServiceImpl implements PlafeedService {
	public static final String GET_BESTSELLERS_BY_CATEGORY_ID = "getBestSellersByCategoryId";
	@Autowired
	PlaFeedRepository plaFeedRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<PlaFeed> search(Integer pageNumber, Integer pageSize, String msn, String gtin, String identifierExists,
			String customLabel2, String customLabel3, String customLabel4, String promotionId, String isGoogleActive,
			String isFacebookActive, String isCriteoActive, String availability) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);

		Specification<PlaFeed> spec = PlaFeedSpecs.getSpecs(msn, gtin, identifierExists, customLabel2, customLabel3,
				customLabel4, promotionId, isGoogleActive, isFacebookActive, isCriteoActive, availability);
		
		return plaFeedRepository.findAll(spec,pageable);
	}

	@Override
	@Cacheable(value = "plaFeed" ,key="#root.target.GET_BESTSELLERS_BY_CATEGORY_ID +#pageNumber +#pageSize +#map.entrySet()" ,unless = "#result == null")
	public String searchWithMap(Integer pageNumber, Integer pageSize, Map<String, Object> map) {
		System.out.println("************"+pageNumber + "******"+pageSize);
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		String plaFeed=null;
		Specification<PlaFeed> spec=GenricSpecification.getSpecification(map);
		List<PlaFeed> plaFeeds=plaFeedRepository.findAll(spec, pageable);
		try {
			plaFeed = objectMapper.writeValueAsString(plaFeeds);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return plaFeed;
	}

	@Override
	@CacheEvict(value = "plaFeed" ,allEntries = true)
	public Boolean evictPlaFeedListAll() {
		return true;
	}

}
