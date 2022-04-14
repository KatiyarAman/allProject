package com.quokka.beantocsv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quokka.beantocsv.entity.PlaFeed;
@Repository
public interface PlaFeedRepository extends JpaRepository<PlaFeed,Long> {

}
