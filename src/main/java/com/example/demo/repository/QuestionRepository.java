package com.example.demo.repository;

import com.example.demo.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer> {

    @Query(value = "select answer from questions where question_id in=questionIds", nativeQuery = true)
    List<String> getAnswersForQuestions(@Param(value = "questionIds") Set<Integer> questionIds);
}
