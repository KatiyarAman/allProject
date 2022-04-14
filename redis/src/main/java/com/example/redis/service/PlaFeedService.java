package com.example.redis.service;

import com.example.redis.entity.PlaFeed;

import java.util.List;
import java.util.Map;

public interface PlaFeedService {

    public String findByColumn(Map<String,String> map );
}
