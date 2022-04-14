package com.quokaa.excel.cms.repository;

import com.quokaa.excel.cms.CmsLayoutComponent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CmsLayoutComponentRepo extends JpaRepository<CmsLayoutComponent,Integer> {
    @Modifying
    @Transactional()
    void deleteByLayoutIdAndIdNotIn(Integer id, List<Integer> ids);
}
