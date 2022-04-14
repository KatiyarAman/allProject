package com.quokaa.excel.repository;

import com.quokaa.excel.entity.ExcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelRepository extends JpaRepository<ExcelEntity,Long> {
}
