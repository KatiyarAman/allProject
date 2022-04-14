package com.db.multi.response;

import java.util.List;
import java.util.Map;

import com.db.multi.csv.entity.PlaFeed;

public interface PlaFeedService {
	public List<PlaFeed> getPlaFeedList(StringBuffer queryParams);
	public PlaFeed getById(Long id);

}
