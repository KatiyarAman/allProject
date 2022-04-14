package com.quokka.csvupload.service.impl;

import com.quokka.csvupload.entities.PlaFeed;
import com.quokka.csvupload.repository.FileUploadRepository;
import com.quokka.csvupload.service.PlaFeedService;
import com.quokka.csvupload.utils.GenricSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class PlaFeedServiceImpl implements PlaFeedService {

    @Autowired
    FileUploadRepository fileUploadRepository;
    @Override
    public List<PlaFeed> getPlaFeedList(Map<String, Object> map) {

        Specification<PlaFeed> specification= GenricSpecification.getSpecs(map,"LIKE");
        Pageable pageable=PageRequest.of(0,200);
        Page<PlaFeed> plaFeeds=fileUploadRepository.findAll(specification,pageable);
        return null;
    }
}
