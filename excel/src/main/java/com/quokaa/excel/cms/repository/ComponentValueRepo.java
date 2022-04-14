package com.quokaa.excel.cms.repository;

import com.quokaa.excel.cms.ComponentValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComponentValueRepo extends JpaRepository<ComponentValue,Integer> {
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByComponentIdAndIdNotIn(Integer id, List<Integer> savedIds);

    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByComponentId(Integer id);
}
