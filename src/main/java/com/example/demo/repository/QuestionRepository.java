package com.example.demo.repository;

import com.example.demo.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    public List<QuestionEntity> findAllOrderByExamId(Integer examId);
}
