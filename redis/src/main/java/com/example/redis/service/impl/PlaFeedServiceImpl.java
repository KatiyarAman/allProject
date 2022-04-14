package com.example.redis.service.impl;

import com.example.redis.entity.PlaFeed;
import com.example.redis.repo.PlaFeedRepo;
import com.example.redis.service.PlaFeedService;
import com.example.redis.utils.PlaFeedSpecs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PlaFeedServiceImpl implements PlaFeedService {
    public static final String GET_BESTSELLERS_BY_CATEGORY_ID = "getBestSellersByCategoryId";
    @Autowired
    PlaFeedRepo plaFeedRepo;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Cacheable(value = "response" ,key="#map.entrySet().toString()" ,unless = "#result == null")
    public String findByColumn(Map<String,String> map) {
        String response="";
        Specification<PlaFeed> spec= PlaFeedSpecs.getSpecs(map);
        Pageable pageable= PageRequest.of(Integer.parseInt((map.get("pageNumber"))),Integer.parseInt(map.get("pageSize")));
        try {
          response=  objectMapper.writeValueAsString(plaFeedRepo.findAll(spec,pageable).getContent());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response ;
    }
}
