package com.example.redis.repo;

import com.example.redis.entity.PlaFeed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaFeedRepo extends JpaRepository<PlaFeed,Long> {
 Page findAll(Specification<PlaFeed> spec, Pageable pageable);
}
