package com.example.demo.controller;

import com.example.demo.requestModel.StudentRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.StudentResponseModel;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<ResponseModel<?>> saveStudent(@Valid @RequestBody StudentRequestModel studentRequestModel) {
        ResponseModel<?> response = studentService.saveStudent(studentRequestModel);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel<?>> deleteStudent(@RequestBody Map<String, Object> deleteRequest) {
        Integer rollNo = (Integer) deleteRequest.get("rollNo");
        String branch = deleteRequest.get("branch").toString();
        if (rollNo != null && !StringUtils.isEmpty(branch)) {
            ResponseModel<?> responseModel = studentService.deleteStudent(rollNo, branch);
            return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
        }

        ResponseModel<?> responseModel = new ResponseModel<>(HttpStatus.BAD_REQUEST, "Error : rollNo or branch missing", null, null);
        return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
    }

    @GetMapping
    public ResponseEntity<ResponseModel<?>> getStudent(@RequestBody Map<String, Object> deleteRequest) {
        Integer rollNo = (Integer) deleteRequest.get("rollNo");
        String branch = deleteRequest.get("branch").toString();
        if (rollNo != null && !StringUtils.isEmpty(branch)) {
            ResponseModel<?> responseModel = studentService.deleteStudent(rollNo, branch);
            return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
        }

        ResponseModel<?> responseModel = new ResponseModel<>(HttpStatus.BAD_REQUEST, "Error : rollNo or branch missing", null, null);
        return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseModel<?>> getStudents() {
        ResponseModel<?> responseModel = studentService.getStudents();
        return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseModel<?>> updateStudent(@Valid @RequestBody StudentRequestModel studentRequestModel) {
        ResponseModel<StudentResponseModel> responseModel = studentService.updateStudent(studentRequestModel);
        return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
    }

    @GetMapping("/students/roll-numbers")
    public ResponseEntity<ResponseModel<?>> getStudentsForRollNo(@RequestParam Set<Integer> rollNumbers) {
        ResponseModel<?> responseModel = studentService.getStudentsForRollNumbers(rollNumbers);
        return new ResponseEntity<>(responseModel, HttpStatus.valueOf(responseModel.getStatus()));
    }
}
