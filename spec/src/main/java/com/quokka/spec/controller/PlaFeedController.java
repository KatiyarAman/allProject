package com.quokka.spec.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quokka.spec.model.PlaFeed;
import com.quokka.spec.repo.PlaFeedRepo;

@RestController
@RequestMapping("/api/*")
public class PlaFeedController {
	
	@Autowired
	private PlaFeedRepo plaFeedRepo;
	
	@GetMapping("list")
	public List<PlaFeed> list(@RequestParam Map<String,Object> param){
		
		System.out.println("map size before "+param.size());
		Integer pagenumber= Integer.valueOf((String)param.get("pageNumber"));
		Integer pageSize= Integer.valueOf((String)param.get("pageSize"));
		param.remove("pageSize");
		param.remove("pageNumber");
		System.out.println("map size before "+param.size() + "pageNumber  " +pagenumber + " pageSize "+pageSize);

		
		return plaFeedRepo.list(param, pagenumber, pageSize);
	}

}
