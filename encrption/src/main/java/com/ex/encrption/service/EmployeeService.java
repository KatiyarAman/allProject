
package com.ex.encrption.service;

import java.util.List;

import com.ex.encrption.entites.Employee;

public interface EmployeeService {
	public Employee createEmployee(Employee employee);

	public Employee findByColumn(String columnName, Object val);

	public List<Employee> list(String columnName, Object value);
}
