package com.quokka.catalog.repository;

import com.quokka.catalog.model.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CassandraRepository<Product,String> {

}
