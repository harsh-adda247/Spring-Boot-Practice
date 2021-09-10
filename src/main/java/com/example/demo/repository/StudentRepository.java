package com.example.demo.repository;

import com.example.demo.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    public void deleteByRollNoAndBranch(Integer rollNo, String branch);

    public StudentEntity findByRollNoAndBranch(Integer rollNo, String branch);
}
