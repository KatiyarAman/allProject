package com.quokka.map.service;

import java.util.List;

import com.quokka.map.entity.Question;

public interface QuestionService {

	public Question save (Question question);
	public Question getById(Long id);
	public List<Question> list();

   List< Question> findAll(Integer pageNumber, Integer pageSize, String search);
}
