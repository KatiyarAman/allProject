package com.ex.encrption.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ex.encrption.entites.Employee;
import com.ex.encrption.entites.Phone;
import com.ex.encrption.model.PhoneType;
import com.ex.encrption.repo.EmployeeRepo;
import com.ex.encrption.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public Employee findByColumn(String columnName, Object val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> list(String columnName, Object value) {
		Specification<Employee> spec=new Specification<Employee>() {

			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Join<Employee,Phone> phoneJoin=root.join("phones");
	              Predicate equalPredicate = criteriaBuilder.equal(phoneJoin.get(columnName), value);

				return equalPredicate;
			}
		};
		return employeeRepo.findAll(spec);
	}

}
