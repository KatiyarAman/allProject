package com.quokka.bms.repo;

import com.quokka.bms.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface BookingDetailsRepo extends JpaRepository<BookingDetails,Long> {
}
