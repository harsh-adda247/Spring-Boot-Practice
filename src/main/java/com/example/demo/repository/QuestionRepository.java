package com.example.demo.repository;

import com.example.demo.entities.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    public List<QuestionEntity> findAllOrderByExamId(Integer examId);

    Page<QuestionEntity> findByAnswerLike(Pageable pageable, String ans);

    Page<QuestionEntity> findByAnswer(Pageable pageable, String answer);
}
