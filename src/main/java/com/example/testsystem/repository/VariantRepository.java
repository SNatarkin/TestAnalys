package com.example.testsystem.repository;

import com.example.testsystem.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<Variant, Integer> {
}
