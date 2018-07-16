package com.example.testsystem.repository;

import com.example.testsystem.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Set<Question> getQuestionsByPart_Name(String name);

}
