package com.db.multi.cms.repository;

import com.db.multi.cms.entity.Component;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<Component,Integer> {
    List<Component> findAll(Specification<Component> componentSpecification);
}
