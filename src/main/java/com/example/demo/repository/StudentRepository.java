package com.example.demo.repository;

import com.example.demo.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    public void deleteByRollNoAndBranch(Integer rollNo, String branch);

    public StudentEntity findByRollNoAndBranch(Integer rollNo, String branch);

    @Query(value = "select * from student where roll_no in :rollNums", nativeQuery = true)
    List<StudentEntity> findStudentsByRollNo(@Param("rollNums") Set<Integer> rollNumbers);


    @Query(value = "select id from student where roll_no in :rollNums order by id asc", nativeQuery = true)
    List<Integer> findIdsByRollNo(@Param("rollNums") List<Integer> rollNumbers);
}
