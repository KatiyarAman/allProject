package com.quokka.searching.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quokka.searching.entity.PlaFeed;
@Repository
public interface PlaFeedRepository extends JpaRepository<PlaFeed,Long>{

	List<PlaFeed> findAll(Specification<PlaFeed> spec, Pageable pageable);

}
