package com.quokaa.excel.service;

import com.quokaa.excel.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee save(Employee employee);
    public Employee getById(Long id);
    public List<Employee> list();
}
