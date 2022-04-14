package com.ex.encrption.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ex.encrption.entites.Employee;
import com.ex.encrption.service.EmployeeService;

@RestController
@RequestMapping("/emp/*")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("createEmployee")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("list")
	public List<Employee>  list() {
		return employeeService.list("type",2);
	}
}
