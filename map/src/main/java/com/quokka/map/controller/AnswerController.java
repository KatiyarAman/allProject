package com.quokka.map.controller;

import com.quokka.map.doa.EmployeeRepository;
import com.quokka.map.domain.Employee;
import com.quokka.map.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import com.quokka.map.entity.Answer;
import com.quokka.map.repository.AnswerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/answer/*")
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;



	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/{id}")
	public Answer getAnswer(@PathVariable("id") Long id) {
		return answerRepository.getById(id);
	}
	@PostMapping("")
	public Employee save(@RequestBody Employee employee){
		return  employeeService.save(employee);
	}

	@GetMapping("/employee/{id}")
	public Employee getById(@PathVariable("id")Long id){
		return employeeService.getById(id);
	}
	@GetMapping("/employee")
	public List<Employee> list(){
		return  employeeService.list();

	}
	
	
	
}
