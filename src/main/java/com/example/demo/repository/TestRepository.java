package com.example.demo.repository;

import com.example.demo.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<TestEntity, Integer> {

    @Query(value = "select *from exam_test where exam_id=:examId order by marks desc limit 10",
            nativeQuery = true)
    public List<TestEntity> getLeaderboardForExam(Integer examId);

    @Query(value = "select *from exam_test where exam_id=:examId and branch=:branch order by marks desc " +
            "limit 10", nativeQuery = true)
    public List<TestEntity> getLeaderboardForExamAndBranch(Integer examId, String branch);
}
