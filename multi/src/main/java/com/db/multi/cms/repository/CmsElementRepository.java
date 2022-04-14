package com.db.multi.cms.repository;

import com.db.multi.cms.entity.CmsElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsElementRepository extends JpaRepository<CmsElement,Integer> {
}
