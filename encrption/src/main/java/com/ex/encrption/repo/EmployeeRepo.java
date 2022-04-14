package com.ex.encrption.repo;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ex.encrption.entites.Employee;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {

	List<Employee> findAll(Specification<Employee> spec);

}
