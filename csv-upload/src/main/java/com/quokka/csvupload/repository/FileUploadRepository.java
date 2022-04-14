package com.quokka.csvupload.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quokka.csvupload.entities.PlaFeed;

import java.util.List;

@Repository
public interface FileUploadRepository extends JpaRepository<PlaFeed,Long> {

    Page<PlaFeed> findAll(Specification<PlaFeed> specification, Pageable pageable);
}
