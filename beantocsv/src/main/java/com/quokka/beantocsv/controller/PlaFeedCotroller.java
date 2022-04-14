package com.quokka.beantocsv.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quokka.beantocsv.entity.PlaFeed;
import com.quokka.beantocsv.model.PlaFeedCSVdump;
import com.quokka.beantocsv.service.PlaFeedService;
import com.quokka.beantocsv.utils.CSVToBean;

@RestController
@RequestMapping("/api/*")
public class PlaFeedCotroller {

	@Autowired
	private PlaFeedService plaFeedService;

	@Autowired
	CSVToBean csvToBean;

	@GetMapping("findAll")
	public org.apache.commons.lang3.tuple.Pair<Boolean, String> findAll() {
		List<PlaFeed> plafeeds = plaFeedService.findAll();

		List<PlaFeedCSVdump> plaFeedCSVdumps = plafeeds.stream().map(it -> PlaFeedCSVdump.bindPlaFeed(it))
				.collect(Collectors.toList());
		return csvToBean.writeFile(plaFeedCSVdumps, PlaFeedCSVdump.class);
	}
}
