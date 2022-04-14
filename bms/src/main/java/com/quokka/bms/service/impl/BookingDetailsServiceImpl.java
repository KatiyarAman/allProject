package com.quokka.bms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.quokka.bms.config.ApplicationConfig;
import com.quokka.bms.entity.AuditBookingDetails;
import com.quokka.bms.entity.BookingDetails;
import com.quokka.bms.repo.AuditBookingDetailsRepo;
import com.quokka.bms.repo.BookingDetailsRepo;
import com.quokka.bms.service.BookingDetailsService;
import com.quokka.bms.utils.ApiManager;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class BookingDetailsServiceImpl implements BookingDetailsService {
    private static final Logger logger= LoggerFactory.getLogger(BookingDetailsServiceImpl.class);
    @Autowired
    BookingDetailsRepo bookingDetailsRepo;
    @Autowired
    AuditBookingDetailsRepo auditDetailsRepo;

    @Autowired
    private ApiManager apiManager;

    @Autowired
    private ApplicationConfig config;

    @Override
    public BookingDetails saveOrUpdate(BookingDetails bookingDetails, String currentUser) {
        logger.info("Persisting Entity {}",new Gson().toJson(bookingDetails));
        bookingDetails.setCreatedBy(currentUser);
        try {

         BookingDetails persistedObj=   bookingDetailsRepo.save(bookingDetails);
         if(persistedObj!=null)
             insertAudit(persistedObj);

         return persistedObj;
        }catch (Exception e){
            logger.error("error occured while persisting the entity :{}",bookingDetails + "by the : "+currentUser +e.getMessage());
       e.printStackTrace();
         return null;
        }
    }
    @Async
    public void insertAudit(BookingDetails persistedObj) {
        logger.info("Got Async Request to persist the entity :{}", new Gson().toJson(persistedObj));
        AuditBookingDetails auditBookingDetails=new AuditBookingDetails(persistedObj);

        try {
            auditDetailsRepo.save(auditBookingDetails);
            JSONObject object= new JSONObject();
            object.put("id",auditBookingDetails.getId());
            object.put("productId",auditBookingDetails.getProductId());
            object.put("categoryId",auditBookingDetails.getCategoryId());
            object.put("status",auditBookingDetails.getStatus());
            object.put("startingAt",String.valueOf(auditBookingDetails.getStartingAt()));
            object.put("endingAt",String.valueOf(auditBookingDetails.getEndingAt()));
            Map<String,String> requestParam= new HashMap<>();
            requestParam.put("user",auditBookingDetails.getCreatedBy());
           /* String response= apiManager.postApi(config.getBooking_url(),requestParam,object);
            logger.info("cassandra booking details :{}",response);*/

        } catch (Exception e) {
            logger.error("error occured while persistig the async entity :{}",new Gson().toJson(auditBookingDetails));
        }
    }
    @Override
    public BookingDetails getById(Long bookingId) {
        logger.info("Get BookingDetails by bookingId : {}",new Gson().toJson(bookingId));
        return bookingDetailsRepo.findById(bookingId).get();
    }

    @Override
    public List<BookingDetails> findAll(Map<String, Object> requestParam, String condition_clause) {
        return null;
    }
}
