package com.quokaa.excel.co;

import lombok.Data;

import javax.persistence.Column;
@Data
public class ExcelEntityRequest {

    private String vertical;
    private String region;
    private Integer teamId;
    private String teamName;
    private String yearMonth;
    private Integer target;

}
