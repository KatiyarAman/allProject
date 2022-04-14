package com.quokka.catalog.repository;

import com.quokka.catalog.model.Category;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends CassandraRepository<Category,String> {

    @AllowFiltering
    Category findAllByIsActive(Boolean isActive);
}
