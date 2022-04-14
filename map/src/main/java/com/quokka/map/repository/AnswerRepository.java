package com.quokka.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quokka.map.entity.Answer;
@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long>{

}
