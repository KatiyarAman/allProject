package com.quokka.bms.controller;

import com.google.gson.Gson;
import com.quokka.bms.entity.BookingDetails;
import com.quokka.bms.model.ResponseModel;
import com.quokka.bms.service.BookingDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking/*")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    private BookingDetailsService detailsService;

    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseModel<BookingDetails> saveOrUpdate(@RequestBody BookingDetails bookingDetails) {
        logger.info("Got request to persist the entity :{} by the user:{}", new Gson().toJson(bookingDetails), "aman");
       return new ResponseModel<>(detailsService.saveOrUpdate(bookingDetails, "aman"));
    }

    @RequestMapping(value = "getbyId/{bookingId}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseModel<BookingDetails> getById(@PathVariable("bookingId") Long bookingId) {
        logger.info("Got Request to get the entity by id: {}", bookingId);
        return new ResponseModel<>(detailsService.getById(bookingId));
    }

    @RequestMapping(value = "findAll", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseModel<List<BookingDetails>> findAll(@RequestParam Map<String, Object> requestParam) {
        logger.info("Got the request to get the entity based on requestParam : {}", new Gson().toJson(requestParam.entrySet()));
        return new ResponseModel<>(detailsService.findAll(requestParam, "OR"));
    }
}
