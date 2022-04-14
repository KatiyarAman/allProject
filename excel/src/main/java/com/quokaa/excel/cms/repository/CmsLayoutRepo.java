package com.quokaa.excel.cms.repository;

import com.quokaa.excel.cms.CmsLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CmsLayoutRepo extends JpaRepository<CmsLayout,Integer> {
}
