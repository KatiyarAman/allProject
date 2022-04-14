package com.quokaa.excel.service;

import com.quokaa.excel.co.ExcelEntityRequest;
import com.quokaa.excel.entity.ExcelEntity;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {

    public Pair<Boolean,String> save(MultipartFile file);

    ExcelEntity getById(Long id);
    List<ExcelEntity> list();

}
