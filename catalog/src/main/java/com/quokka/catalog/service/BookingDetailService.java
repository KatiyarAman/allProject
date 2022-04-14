package com.quokka.catalog.service;

import com.quokka.catalog.model.BookingDetails;

public interface BookingDetailService {
    BookingDetails saveOrUpdate(BookingDetails bookingDetails,String currentUser);
    BookingDetails getById(String id);
}
