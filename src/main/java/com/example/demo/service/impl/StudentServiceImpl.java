package com.example.demo.service.impl;

import com.example.demo.entities.StudentEntity;
import com.example.demo.repository.StudentRepository;
import com.example.demo.requestModel.StudentRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.StudentResponseModel;
import com.example.demo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private static ModelMapper modelMapper = new ModelMapper();

    public StudentServiceImpl() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * method to save a student
     *
     * @param studentRequestModel
     * @return
     */
    @Override
    public ResponseModel<StudentResponseModel> saveStudent(StudentRequestModel studentRequestModel) {
        logger.info("**** Inside StudentServiceImpl ==> saveStudent ****");
        StudentEntity entity = null;
        try {
            entity = modelMapper.map(studentRequestModel, StudentEntity.class);
            entity = studentRepository.save(entity);
        } catch (Exception e) {
            logger.error("Error occurred while saving the student entity : " + e.getMessage());
            return new ResponseModel<>(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't save the student", null, null);
        }

        logger.info("student saved successfully");
        StudentResponseModel response = modelMapper.map(entity, StudentResponseModel.class);
        return new ResponseModel<>(HttpStatus.OK, "Student saved successfully", null, response);
    }

    /**
     * @param rollNo
     * @param branch
     * @return
     */
    @Override
    public ResponseModel<?> deleteStudent(Integer rollNo, String branch) {
        logger.info("**** Inside StudentServiceImpl ==> deleteStudent ****");
        StudentEntity entity = studentRepository.findByRollNoAndBranch(rollNo, branch);
        if (entity == null) {
            logger.info("Error no such student entity found for roll no " + rollNo + " and branch " + branch);
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "No such student found for rollNo " + rollNo + " and branch " + branch, null, null);
        }

        studentRepository.delete(entity);
        logger.info("student entity is deleted successfully");
        return new ResponseModel<>(HttpStatus.OK, "Student deleted successfully", null, null);
    }

    /**
     * method to retrieve a student corresponding to provided rollNo and branch
     *
     * @param rollNo
     * @param branch
     * @return
     */
    @Override
    public ResponseModel<StudentResponseModel> getStudent(Integer rollNo, String branch) {
        StudentEntity entity = studentRepository.findByRollNoAndBranch(rollNo, branch);
        if (entity == null) {
            return new ResponseModel<>(HttpStatus.OK, "No such student found for roll no " + rollNo + " and branch " + branch, null, null);
        }
        logger.info("student retrieved successfully");
        StudentResponseModel studentResponseModel = modelMapper.map(entity, StudentResponseModel.class);
        return new ResponseModel<StudentResponseModel>(HttpStatus.OK, "Student retrieved successfully", null, studentResponseModel);
    }

    /**
     * method to retrieve list of students
     *
     * @return
     */
    @Override
    public ResponseModel<List<StudentResponseModel>> getStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        if (studentEntities == null || studentEntities.size() == 0) {
            return new ResponseModel<>(HttpStatus.OK, "No students found", null, null);
        }
        logger.info("students retrieved successfully");
        List<StudentResponseModel> students = generateListOfStudentsResponse(studentEntities.size() - 1, studentEntities);
        return new ResponseModel<List<StudentResponseModel>>(HttpStatus.OK, "Student retrieved successfully", null, students);
    }

    /**
     * method to update an existing student entity
     *
     * @param studentRequestModel
     * @return
     */
    @Override
    public ResponseModel<StudentResponseModel> updateStudent(StudentRequestModel studentRequestModel) {
        logger.info("Inside StudentServiceImpl ==> updateStudent");
        Integer rollNo = studentRequestModel.getRollNo();
        String branch = studentRequestModel.getBranch();
        StudentEntity entity = studentRepository.findByRollNoAndBranch(rollNo, branch);
        if (entity == null) {
            logger.info("No such student found for rollNo " + rollNo + " and branch " + branch);
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "No such student found for rollNo " + rollNo + " and branch " + branch, null, new StudentResponseModel());
        }
        logger.info("Student entity found to be updated");
        studentRequestModel.setId(entity.getId());
        entity = modelMapper.map(studentRequestModel, StudentEntity.class);
        entity = studentRepository.save(entity);
        logger.info("student updated successfully");
        StudentResponseModel studentResponseModel = modelMapper.map(entity, StudentResponseModel.class);
        return new ResponseModel<>(HttpStatus.OK, "Student updated successfully", null, studentResponseModel);
    }

    /**
     * method to generate list of student responses
     *
     * @param index
     * @param students
     * @return
     */
    private static List<StudentResponseModel> generateListOfStudentsResponse(int index, List<StudentEntity> students) {
        //Base Case
        if (index < 0) return new ArrayList<StudentResponseModel>();
        //faith
        List<StudentResponseModel> studentResponseModels = generateListOfStudentsResponse(index - 1, students);
        //faith * expectation
        StudentResponseModel student = modelMapper.map(students.get(index), StudentResponseModel.class);
        studentResponseModels.add(student);
        return studentResponseModels;
    }
}
