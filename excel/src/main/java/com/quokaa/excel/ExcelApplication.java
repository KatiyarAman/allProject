package com.quokaa.excel;

import com.quokaa.excel.entity.Employee;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.quokaa.excel.mapper.EmployeeMapper")
@SpringBootApplication
public class ExcelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelApplication.class, args);
	}

}
