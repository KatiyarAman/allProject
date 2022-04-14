package com.quokaa.excel.entity;

import com.quokaa.excel.model.ExcelModel;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name = "excel_entity")
public class ExcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="vertical")
    private String vertical;
    @Column(name = "region")
    private String region;
    @Column(name = "team_id")
    private Double teamId;
    @Column(name = "team_name")
    private String teamName;
    @Column(name = "month_year")
    private String monthYear;
    @Column(name = "target")
    private double target;
    @Column(name="created_at")
    private Timestamp timestamp;

    public ExcelEntity(String vertical, String region, double teamId, String teamName, String monthYear, double target) {
   this.vertical=vertical;
   this.region=region;
   this.teamId=teamId;
   this.teamName=teamName;
   this.monthYear=monthYear;
   this.target=target;
    }

    public ExcelEntity() {
    }

    public static ExcelEntity bindExcelModel(ExcelModel it) {
        ExcelEntity excelEntity= new ExcelEntity();
        excelEntity.setVertical(it.getVertical());
        excelEntity.setRegion(it.getRegion());
        excelEntity.setTeamId(it.getTeamId());
        excelEntity.setTeamName(it.getTeamName());
        excelEntity.setMonthYear(it.getMonthYear());
        excelEntity.setTarget(it.getTarget());
        if(it.getTimeStamp()!=null) {
            excelEntity.setTimestamp(validateTimeStamp(it.getTimeStamp()));
        }
        return excelEntity;
    }

    private static Timestamp validateTimeStamp(String timeStamp) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy hh:mm");
            Date date = df.parse(timeStamp);
            return  new Timestamp(date.getTime());
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
}
