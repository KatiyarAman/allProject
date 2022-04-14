package com.quokka.bms.service;

import com.quokka.bms.entity.BookingDetails;

import java.util.List;
import java.util.Map;

public interface BookingDetailsService {
    BookingDetails saveOrUpdate(BookingDetails bookingDetails,String currentUser);
    BookingDetails getById(Long bookingId);
    List<BookingDetails> findAll(Map<String,Object> requestParam ,String condition_clause);
}
