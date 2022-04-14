package com.quokaa.excel.controller;

import com.quokaa.excel.entity.Employee;
import com.quokaa.excel.model.ResponseModel;
import com.quokaa.excel.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.List;

@RestController
@RequestMapping("/api/employee/*")
public class EmployeeController {

    private static final Logger logger= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("add")
    public Employee save(@RequestBody Employee employee){
        logger.info("Got the request to persist the employee" +employee);
        return employeeService.save(employee);
    }

    @GetMapping("employeeList")
    public List<Employee> list(){
        return  employeeService.list();
    }

    @GetMapping("getById/{id}")
    public Employee getById(@PathVariable("id")Long id){return  employeeService.getById(id);}
}
