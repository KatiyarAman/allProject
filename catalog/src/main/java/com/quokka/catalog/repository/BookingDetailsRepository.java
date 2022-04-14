package com.quokka.catalog.repository;

import com.quokka.catalog.model.BookingDetails;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailsRepository extends CassandraRepository<BookingDetails,String> {
}
