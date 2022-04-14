package com.quokka.catalog.controller;

import com.google.gson.Gson;
import com.quokka.catalog.dto.BookingDetailsDTO;
import com.quokka.catalog.model.BookingDetails;
import com.quokka.catalog.service.BookingDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking/*")
public class BookingDetailsController {
    private static final Logger logger= LoggerFactory.getLogger(BookingDetailsController.class);

    @Autowired
    private BookingDetailService detailService;

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public BookingDetails saveOrUpdate(@RequestBody BookingDetails details, @RequestParam("user")String currentUser){
        logger.info("Got requets to persist the entity '{}' by user '{}'",new Gson().toJson(details),currentUser);
        return detailService.saveOrUpdate(details,currentUser);
       // return details;
    }
}
