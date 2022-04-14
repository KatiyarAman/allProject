package com.quokaa.excel.cms.repository;

import com.quokaa.excel.cms.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepo extends JpaRepository<Component,Integer> {
}
