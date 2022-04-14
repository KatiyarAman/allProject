package com.quokka.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.quokka.map.entity.Question;
import com.quokka.map.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@PostMapping("")
	public Question save(@RequestBody Question question) {
		return questionService.save(question);
	}

	@GetMapping("")
	public List<Question> list(@RequestParam(defaultValue = "0",required = false)Integer pageNumber,
							   @RequestParam(defaultValue = "10",required = false)Integer pageSize,
							   @RequestParam(required = false)String search){

		return  questionService.findAll(pageNumber,pageSize,search);
	}
	@GetMapping("/{id}")
	public Question getById(@PathVariable("id")Long id){
		return questionService.getById(id);
	}
	@GetMapping("questions")
	public List<Question> getAll(){return questionService.list();}
}
