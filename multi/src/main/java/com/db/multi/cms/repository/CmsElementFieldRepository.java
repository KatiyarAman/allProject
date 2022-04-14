package com.db.multi.cms.repository;

import com.db.multi.cms.entity.CmsElementField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsElementFieldRepository extends JpaRepository<CmsElementField,Integer> {
    void deleteByElementIdAndIdNotIn(Integer id, List<Integer> ids);
}
