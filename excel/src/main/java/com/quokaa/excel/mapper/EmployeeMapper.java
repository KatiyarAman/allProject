package com.quokaa.excel.mapper;

import com.quokaa.excel.entity.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface EmployeeMapper {


    @Insert("insert into employee (employeeName) values( #{employeeName})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void insertEmployee(Employee employee);

    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "employeeName",column = "employeeName")
    })
    @Select("select id ,employeeName from employee")
    List<Employee> listEmployee();
}
