package com.quokka.bms.repo;

import com.quokka.bms.entity.AuditBookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditBookingDetailsRepo extends JpaRepository<AuditBookingDetails,Long> {
}
