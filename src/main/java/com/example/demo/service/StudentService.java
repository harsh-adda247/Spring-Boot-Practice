package com.example.demo.service;

import com.example.demo.requestModel.StudentRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.StudentResponseModel;

import java.util.List;
import java.util.Set;


public interface StudentService {

    /**
     * method to save a student entity
     *
     * @param studentRequestModel
     * @return
     */
    ResponseModel<StudentResponseModel> saveStudent(StudentRequestModel studentRequestModel);

    /**
     * method to delete a student entity
     *
     * @param rollNo
     * @param branch
     * @return
     */
    ResponseModel<?> deleteStudent(Integer rollNo, String branch);

    /**
     * method to retrieve a student corresponding to provided rollNo and branch
     *
     * @param rollNo
     * @param branch
     * @return
     */
    ResponseModel<StudentResponseModel> getStudent(Integer rollNo, String branch);

    /**
     * method to retrieve list of students
     *
     * @return
     */
    ResponseModel<List<StudentResponseModel>> getStudents();

    /**
     * method to update an existing student entity
     *
     * @param studentRequestModel
     * @return
     */
    ResponseModel<StudentResponseModel> updateStudent(StudentRequestModel studentRequestModel);

    /**
     * method to return list of students for the provided roll numbers
     * @param rollNumbers
     * @return
     */
    ResponseModel<List<StudentResponseModel>> getStudentsForRollNumbers(Set<Integer> rollNumbers);
}
