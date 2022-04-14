package com.quokka.beantocsv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quokka.beantocsv.entity.PlaFeed;
import com.quokka.beantocsv.repository.PlaFeedRepository;

@Service
public class PlaFeedServiceImpl implements PlaFeedService {

	@Autowired
	private PlaFeedRepository plafeedRepository;

	@Override
	public List<PlaFeed> findAll() {
		return plafeedRepository.findAll();
	}

}
