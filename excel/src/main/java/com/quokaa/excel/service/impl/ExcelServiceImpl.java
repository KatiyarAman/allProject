package com.quokaa.excel.service.impl;

import com.poiji.bind.Poiji;
import com.quokaa.excel.config.ApplicationConfig;
import com.quokaa.excel.csv.ExcelFaildRows;
import com.quokaa.excel.entity.ExcelEntity;
import com.quokaa.excel.model.ExcelModel;
import com.quokaa.excel.repository.ExcelRepository;
import com.quokaa.excel.service.ExcelService;
import com.quokaa.excel.service.FileUploadService;
import org.apache.poi.hssf.record.cf.IconMultiStateThreshold;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExcelServiceImpl implements ExcelService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
    @Autowired
    ExcelRepository excelRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    ApplicationConfig config;

    @Qualifier(value = "excelJdbc")
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Pair<Boolean, String> save(MultipartFile file) {
        Pair<Boolean, String> storedFile = fileUploadService.storeFile(file);

        List<ExcelModel> employees = Poiji.fromExcel(new File(config.getRootDir() + storedFile.getSecond()), ExcelModel.class);


        List<ExcelFaildRows> faildRows = mapInsertRecord(employees);
        //        if(storedFile.getFirst())
//        {
//            System.out.println("Stored file");
//        }
//        List<ExcelEntity> excelEntityList= new ArrayList<>();
//        try(InputStream inputStream= new FileInputStream(config.getRootDir()+storedFile.getSecond())){
//            XSSFWorkbook xssfWorkbook= new XSSFWorkbook(inputStream);
//            XSSFSheet sheet=xssfWorkbook.getSheet("Sheet1");
//            int rowNumber=0;
//           for(Row row:sheet){
//               if (rowNumber==0)
//               {
//                   rowNumber++;
//                   continue;
//               }
//
//               String vertical=row.getCell(0).getStringCellValue();
//               String region=row.getCell(1).getStringCellValue();
//               double teamId=row.getCell(2).getNumericCellValue();
//               String teamName=row.getCell(3).getStringCellValue();
//               String monthYear=row.getCell(4).getStringCellValue();
//               double target=row.getCell(5).getNumericCellValue();
//               ExcelEntity excelEntity= new ExcelEntity(vertical,region,teamId,teamName,monthYear,target);
//           excelEntityList.add(excelEntity);
//           }
//           excelRepository.saveAll(excelEntityList);
//           return Pair.of(true, "inserted successfully");
//
//        }catch(Exception e){
//            e.printStackTrace();
//            return Pair.of(false, "inserted failed");
//
//        }
        return Pair.of(true, "successfully inserted number of records " + faildRows.size());
    }

    @Override
   // @Transactional("txnExcel")
    public ExcelEntity getById(Long id) {
    	logger.info("Request for the excel entity by the id  : "+ id);
        return excelRepository.findById(id).get();
    }

    @Override
    public List<ExcelEntity> list() {
        File file =this.fileUploadService.writeInFile("excelEntity","excel");
        String sql="select * from excel_entity";
        return jdbcTemplate.query(sql,(rs, rowNum) -> {
            try(FileWriter fileWriter= new FileWriter(file)){
                fileWriter.write(rs.getString(1) + " " +rs.getString(2)
                + rs.getString(3)+rs.getString(4)+rs.getString(5)
                +rs.getString(6));

            }catch (IOException exception){
                exception.getMessage();
            }
            return new ExcelEntity();
        });
    }

    private List<ExcelFaildRows> mapInsertRecord(List<ExcelModel> employees) {
        List<ExcelModel> insertionExcelModels = new ArrayList<>();
        List<ExcelFaildRows> excelFaildRows = new ArrayList<>();
        for (ExcelModel excelModel : employees) {
            //validating the excelModel
            Pair<Boolean, ExcelFaildRows> validationPair = validationPair(excelModel);
            if (!validationPair.getFirst()) {
                excelFaildRows.add(validationPair.getSecond());
                continue;
            }
            insertionExcelModels.add(excelModel);
        }

        Pair<Boolean, ExcelFaildRows> createExcelEntityPair = createExcelEntity(insertionExcelModels);
        if (!createExcelEntityPair.getFirst())
            excelFaildRows.add(createExcelEntityPair.getSecond());
        return excelFaildRows;
    }

    private Pair<Boolean, ExcelFaildRows> createExcelEntity(List<ExcelModel> excelModel) {
        List<ExcelEntity> excelEntityList = excelModel.stream().map(it -> ExcelEntity.bindExcelModel(it)).collect(Collectors.toList());
        List<ExcelEntity> chunks = new ArrayList<>();
        try {

            for (int i = 0; i < excelEntityList.size(); i += 100) {
                chunks = excelEntityList.subList(i, i + Math.min(excelEntityList.size() - i, 100));
                insertExcelData(chunks);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Pair.of(false, new ExcelFaildRows(chunks.stream().findAny().get()));
        }
        return Pair.of(true, new ExcelFaildRows());
    }

    private Pair<Boolean, ExcelFaildRows> validationPair(ExcelModel excelModel) {
        if (StringUtils.isBlank(excelModel.getTimeStamp()))
            return Pair.of(false, new ExcelFaildRows(excelModel));
        else if (StringUtils.isBlank(excelModel.getVertical()))
            return Pair.of(false, new ExcelFaildRows(excelModel));
        else if (StringUtils.isBlank(excelModel.getRegion()))
            return Pair.of(false, new ExcelFaildRows(excelModel));
        else if (StringUtils.isBlank(excelModel.getTeamName()))
            return Pair.of(false, new ExcelFaildRows(excelModel));
        else if (StringUtils.isBlank(excelModel.getMonthYear()))
            return Pair.of(false, new ExcelFaildRows(excelModel));
        else if (excelModel.getTarget() > 0 && excelModel.getTeamId() > 0)
            return Pair.of(false, new ExcelFaildRows(excelModel));
        else
            return Pair.of(true, new ExcelFaildRows());
    }

    @Transactional
    public int[] insertExcelData(List<ExcelEntity> employees) {

        String sql = "insert into excel_entity(id,vertical,region,team_id,team_name,month_year,target,created_at) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, null);
                ExcelEntity excelModel = employees.get(i);
                ps.setString(2, excelModel.getVertical());
                ps.setString(3, excelModel.getRegion());
                ps.setDouble(4, excelModel.getTeamId());
                ps.setString(5, excelModel.getTeamName());
                ps.setString(6, excelModel.getMonthYear());
                ps.setDouble(7, excelModel.getTarget());
                ps.setTimestamp(8, excelModel.getTimestamp());

            }

            @Override
            public int getBatchSize() {
                return employees.size();
            }
        });

    }
}
