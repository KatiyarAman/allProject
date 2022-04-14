package com.quokka.map.mapper;

import com.quokka.map.entity.Question;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
public interface QuestionMapper {

    @Results(value = {
            @Result(property = "id",column = "id"),
            @Result(property = "questionName",column = "question_name")
    })
    @Select("select * from question ")
    List<Question> getAllQuestion();
}
