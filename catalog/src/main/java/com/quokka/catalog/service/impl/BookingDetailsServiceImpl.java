package com.quokka.catalog.service.impl;

import com.google.gson.Gson;
import com.quokka.catalog.model.BookingDetails;
import com.quokka.catalog.repository.BookingDetailsRepository;
import com.quokka.catalog.service.BookingDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingDetailsServiceImpl implements BookingDetailService {
    private static final Logger logger= LoggerFactory.getLogger(BookingDetailsServiceImpl.class);

    @Autowired
    private BookingDetailsRepository  detailsRepository;

    @Override
    public BookingDetails saveOrUpdate(BookingDetails bookingDetails, String currentUser) {
        logger.info("Got request to persist the entity by user '{}' and entity '{}'",currentUser,new Gson().toJson(bookingDetails) );
        try {
            bookingDetails.setCreatedBy(currentUser);
          return   detailsRepository.save(bookingDetails);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("error occured while persisting the entity '{}' by user '{}'",new Gson().toJson(bookingDetails),currentUser);
        return null;
        }

    }

    @Override
    public BookingDetails getById(String id) {
        logger.info("Request to get entity by id '{}'",id);
        return detailsRepository.findById(id).get();
    }
}
