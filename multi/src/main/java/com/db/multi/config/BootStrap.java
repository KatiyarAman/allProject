package com.db.multi.config;

import com.db.multi.cms.repository.ComponentRepository;
import com.db.multi.csv.repo.PlaFeedRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {
    private static final Logger logger= LoggerFactory.getLogger(BootStrap.class);

    @Autowired
    ComponentRepository componentRepository;
    @Autowired
    PlaFeedRepo plaFeedRepo;
    @Override
    public void run(String... args) throws Exception {

       logger.info("&*"+plaFeedRepo.getById(9991));
        logger.info("******" +componentRepository.findAll());

    }
}
