package com.quokaa.excel.csv;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelRow;
import com.quokaa.excel.entity.ExcelEntity;
import com.quokaa.excel.model.ExcelModel;
import lombok.Data;

@Data
public class ExcelFaildRows {
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

    public ExcelFaildRows(ExcelModel excelModel) {
        this.vertical=excelModel.getVertical();
        this.region=excelModel.getRegion();
        this.teamId=excelModel.getTeamId();
        this.teamName=excelModel.getTeamName();
        this.monthYear=excelModel.getMonthYear();
        this.target= excelModel.getTarget();
        this.timeStamp=excelModel.getTimeStamp();

    }
    public ExcelFaildRows(){}

    public ExcelFaildRows(ExcelEntity excelEntity) {
        this.vertical=excelEntity.getVertical();
        this.region=excelEntity.getRegion();
        this.teamId=excelEntity.getTeamId();
        this.teamName=excelEntity.getTeamName();
        this.monthYear=excelEntity.getMonthYear();
        this.target= excelEntity.getTarget();
       // this.timeStamp=excelEntity.getTimeStamp();
    }
}
