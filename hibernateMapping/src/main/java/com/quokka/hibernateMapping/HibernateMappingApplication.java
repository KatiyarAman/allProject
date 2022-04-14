package com.quokka.hibernateMapping;

import com.quokka.hibernateMapping.entity.Answer;
import com.quokka.hibernateMapping.entity.Question;
import com.quokka.hibernateMapping.repository.QuestionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HibernateMappingApplication {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(HibernateMappingApplication.class, args);
		QuestionRepository questionRepository=context.getBean(QuestionRepository.class);

		Question question= new Question();
		question.setId(1L);
		question.setQuestionName("what do you mean by polymorphysm");

		Question question1=new Question();
		question1.setId(2L);
		question1.setQuestionName("Why redis ..?");

		Question question2= new Question();
		question2.setId(3L);
		question2.setQuestionName("Why kafka");

		Answer answer= new Answer();
		answer.setAnswerId(1L);
		answer.setAnswerName("we can use multiple time oe function");
answer.setQuestion(question);
		Answer answer1= new Answer();
		answer1.setAnswerId(2L);
		answer1.setAnswerName("Redish is for the cache based application");
		answer1.setQuestion(question);

		Answer answer2= new Answer();
		answer2.setAnswerId(3L);
		answer2.setAnswerName("Message sending ");
		answer2.setQuestion(question);

		List<Answer> answerList= new ArrayList<>();
		answerList.add(answer);
		answerList.add(answer1);
		answerList.add(answer2);

		question.setAnswers(answerList);
		questionRepository.save(question);
		List<Question> questionList=questionRepository.findAll();
		for (Question question3:questionList){
			System.out.println(question3);
		}
	}

}
