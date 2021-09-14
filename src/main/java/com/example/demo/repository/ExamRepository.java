package com.example.demo.repository;

import com.example.demo.entities.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
}
