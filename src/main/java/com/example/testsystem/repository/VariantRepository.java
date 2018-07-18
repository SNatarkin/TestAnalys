package com.example.testsystem.repository;

import com.example.testsystem.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VariantRepository extends JpaRepository<Variant, Integer> {
    List<Variant> findByQuestionId(Integer questionId);

    @Query("SELECT v FROM Variant v WHERE v.question.part.id = ?1")
    List<Variant> findAllByPartId(Integer partId);
}
