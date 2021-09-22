package com.example.demo.repository;

import com.example.demo.entities.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {

    /** We use @Modifying & @Transactional when we are inserting, deleting or updating something via
     * native query **/
    @Modifying
    @Transactional
    @Query(value = "delete from exams where exam_id=:examId", nativeQuery = true)
    int deleteExamOfExamId(Integer examId);
}
