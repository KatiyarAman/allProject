package com.quokka.map.service;

import com.quokka.map.domain.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee save(Employee employee);
    public Employee getById(Long empId);
    public List<Employee> list();

}
