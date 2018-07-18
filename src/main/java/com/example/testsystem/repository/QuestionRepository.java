package com.example.testsystem.repository;

import com.example.testsystem.model.Question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Page<Question> findByPartId(Integer partId, Pageable pageable);

    List<Question> findByPartId(Integer partId);


}
