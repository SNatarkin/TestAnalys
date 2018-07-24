package com.example.testsystem.repository;

import com.example.testsystem.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> getAnswersByUserId(Integer userId);
}
