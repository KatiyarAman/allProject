package com.quokaa.excel.cms.repository;

import com.quokaa.excel.cms.CmsLayoutValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CmsLayoutValueRepo extends JpaRepository<CmsLayoutValue,Integer> {
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByLayoutIdAndIdNotIn(Integer id, List<Integer> ids);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByLayoutId(Integer id);
}
