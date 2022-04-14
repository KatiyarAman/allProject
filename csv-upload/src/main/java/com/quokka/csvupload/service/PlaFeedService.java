package com.quokka.csvupload.service;

import com.quokka.csvupload.entities.PlaFeed;

import java.util.List;
import java.util.Map;

public interface PlaFeedService {
    public List<PlaFeed> getPlaFeedList(Map<String,Object> map);
}
