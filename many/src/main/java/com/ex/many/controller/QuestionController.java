package com.ex.many.controller;

import com.ex.many.entity.Question;
import com.ex.many.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/add")
    public Question save(@RequestBody Question question){
        return questionService.save(question);
       // return  question;
    }
    @GetMapping ("")
    public List<Question> get(){
        return questionService.list();
        // return  question;
    }
}
