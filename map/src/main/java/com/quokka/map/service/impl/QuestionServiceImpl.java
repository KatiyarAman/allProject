package com.quokka.map.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.quokka.map.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quokka.map.entity.Answer;
import com.quokka.map.entity.Question;
import com.quokka.map.repository.QuestionRepository;
import com.quokka.map.service.QuestionService;

import javax.persistence.criteria.*;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

//	@Autowired
//	QuestionMapper questionMapper;

	@Override
	public Question save(Question question) {
		
		List<Answer> answers=question.getAnswers();
		
		List<Answer> aList= new ArrayList<Answer>();
		for (Answer answer : answers) {
			answer.setQuestion(question);
			aList.add(answer);
		}
		question.setAnswers(aList);
		
		return questionRepository.save(question);
	}

	@Override
	@Transactional
	public Question getById(Long id) {
		return questionRepository.getById(id);
	}

	@Override
	public List<Question> list() {
		// TODO Auto-generated method stub
		//return questionMapper.getAllQuestion();
		return null;
	}

	@Override
	public List<Question> findAll(Integer pageNumber, Integer pageSize, String search) {
Map<String,Object> map= new HashMap<>();
//map.put("questionName","what is kafka");
map.put("id",7L);
		Pageable pageable= PageRequest.of(pageNumber,pageSize);
		List<Question>questionList=questionRepository.findAll(new Specification<Question>() {
			@Override
			public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate= criteriaBuilder.conjunction();

				predicate=criteriaBuilder.and(criteriaBuilder.like(root.get("questionName"),"%"+search+"%"));
//Join<Question,Answer> join=root.join("answers");
//				for (Map.Entry<String ,Object> entry:map.entrySet()){
//					predicate = criteriaBuilder.equal(root.get(entry.getKey()), (Long)entry.getValue());
//					//predicate=predicate!=null?criteriaBuilder.and(criteriaBuilder.like(root.get(entry.getKey()),"%"+entry.getValue()+"%")):predicate;
//				}
			//predicate=criteriaBuilder.and(criteriaBuilder.like(join.get("answerName"),"%"+search+"%"));
			 return  predicate;
			}
		},pageable);
		return questionList;
	}

}
