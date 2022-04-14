package com.ex.many.service;

import com.ex.many.entity.Question;

import java.util.List;

public interface QuestionService {

    public Question save(Question question);
    public List<Question> list();
    public Question getById(Long id);
}
