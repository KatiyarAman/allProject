package com.ex.many.service.impl;

import com.ex.many.entity.Answer;
import com.ex.many.entity.Question;
import com.ex.many.repository.QuestionRepository;
import com.ex.many.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuetionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question save(Question question) {
          // List<Answer> answers=question.getAnswers().stream().map(it->it.setQuestion(q)).collect(Collectors.toList());
List<Answer> answers=question.getAnswers();

List<Answer> answerList= new ArrayList<>();
for (Answer answer:answers){
    answer.setQuestion(question);
    answerList.add(answer);
}

//
        question.setAnswers(answerList);
        return questionRepository.save(question);
    }

    @Override
    public List<Question> list() {
        return questionRepository.findAll();
    }

    @Override
    public Question getById(Long id) {
        return null;
    }
}
