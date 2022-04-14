package com.quokaa.excel.model;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;
import lombok.Data;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlElements;
import java.sql.Date;
import java.sql.Timestamp;

@Data
public class ExcelModel {
    @ExcelRow
    private int id;
    @ExcelCell(0)
    private String vertical;
    @ExcelCell(1)
    private String region;
    @ExcelCell(2)
    private Double teamId;
    @ExcelCell(3)
    private String teamName;
    @ExcelCell(4)
    private String monthYear;
    @ExcelCell(5)
    private double target;
    @ExcelCell(6)
    private String timeStamp;
}
