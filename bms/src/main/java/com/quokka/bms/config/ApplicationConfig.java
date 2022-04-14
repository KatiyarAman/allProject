package com.quokka.bms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {
    @Value("${booking-url}")
    private String booking_url;

    public String getBooking_url() {
        return booking_url;
    }

}
