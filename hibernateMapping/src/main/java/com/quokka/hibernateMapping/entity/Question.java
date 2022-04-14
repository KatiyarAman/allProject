package com.quokka.hibernateMapping.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="question_table")
public class Question {
    @Id
    private Long id;

    private String questionName;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "question",fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Answer> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionName='" + questionName + '\'' +
                ", answers=" + answers +
                '}';
    }
}
