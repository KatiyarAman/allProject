package com.db.multi.cms.repository;

import com.db.multi.cms.entity.ComponentValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComponentValueRepo  extends JpaRepository<ComponentValue,Long> {
    @Modifying
    @Transactional(propagation = Propagation.REQUIRED)
    void deleteByComponentIdAndIdNotIn(Integer id, List<Integer> ids);
}
