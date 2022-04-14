package com.quokka.map.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quokka.map.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository  extends JpaRepository<Question,Long>{

    List<Question> findAll(Specification<Question> questionName, Pageable pageable);
}
