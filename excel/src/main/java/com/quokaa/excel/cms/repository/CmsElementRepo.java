package com.quokaa.excel.cms.repository;

import com.quokaa.excel.cms.CmsElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsElementRepo  extends JpaRepository<CmsElement,Integer> {
}
