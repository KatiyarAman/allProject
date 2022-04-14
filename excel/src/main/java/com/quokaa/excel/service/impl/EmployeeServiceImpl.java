package com.quokaa.excel.service.impl;

import com.quokaa.excel.entity.Employee;
import com.quokaa.excel.mapper.EmployeeMapper;
import com.quokaa.excel.repository.EmployeeRepository;
import com.quokaa.excel.service.EmployeeService;
import com.quokaa.excel.service.ExcelService;
import com.quokaa.excel.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {
private static  final Logger logger= LoggerFactory.getLogger(EmployeeServiceImpl.class);
   @Autowired
    private EmployeeMapper employeeMapper;
   
   @Qualifier(value = "excelJdbc")
   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Autowired
   private EmployeeRepository employeeRepository;

   @Autowired
   private FileUploadService fileUploadService;

    @Override
    public Employee save(Employee employee) {
        logger.info("Entity is going to persist "+employee);
       // employeeRepository.save(employee);

       // employeeMapper.insertEmployee(employee);
        //employee.setId(employee.getId());
        Employee employee1=employeeSave(employee);
        
        return  employee1!=null?employee:employee1;
        //return employeeUpdate(employee);
    }
    Employee employeeSave(Employee employee){
        String sql="insert into employee (id,employeeName,created_at,modified_at) values(?,?,NOW(),NOW())";
        jdbcTemplate.execute(sql, (PreparedStatementCallback<Boolean>) ps->{
            ps.setString(1,null);
            ps.setString(2,employee.getEmployeeName());
            return ps.execute();
        });
        return null;
    }
    public Employee employeeUpdate(Employee employee){
        String sql="update employee set `employeeName` =?,`modified_at`=NOW() where `id` = ?";
       return jdbcTemplate.execute(sql, (PreparedStatementCallback<Employee>) ps -> {
           ps.setString(1,employee.getEmployeeName());
           ps.setLong(2,employee.getId());
           ps.execute();
          ResultSet resultSet= ps.executeQuery();
          resultSet.next();
           Employee employee1= new Employee(resultSet.getLong(1),resultSet.getString(2));
           return null;
       });
    }


    @Override
    public Employee getById(Long id) {
        String sql="select id,employeeName,created_at,modified_at from employee where id = :ids";
        Map<String ,Long> map=new HashMap<>();
        map.put("ids",id);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate= new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
       return namedParameterJdbcTemplate.query(sql, map, rs -> {
           rs.next();

         return new Employee(rs.getLong(1),rs.getString(2));
        });
    }

    @Override
    public List<Employee> list() {
        String sql="select * from employee ";
       // return  jdbcTemplate.query(sql,(rs,num)-> new Employee(rs.getLong(1),rs.getString(2)));
       // return null;
     File file=   this.fileUploadService.writeInFile("abc","aman");

        return jdbcTemplate.query(sql,(rs, rowNum) ->{
            try(FileWriter fileWriter= new FileWriter(file)) {
                fileWriter.write(rs.getString(1)+ " "+ rs.getString(2) );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  new Employee(rs.getLong(1),rs.getString(2));
       });
    }
}
