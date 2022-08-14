package com.example.demo.controller;

import com.example.demo.entities.DemoEntity;
import com.example.demo.repository.DemoRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.requestModel.StudentRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.responseModel.StudentResponseModel;
import com.example.demo.service.StudentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DemoRepository demoRepository;

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

    @GetMapping(value = "/test/response")
    public ResponseEntity getResponse(@RequestHeader String token) throws UnsupportedEncodingException, JsonProcessingException {
        String payload = token.split("\\.")[1];
        String finalPayload = new String(Base64.decodeBase64(payload), "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        TestPayload testPayload = objectMapper.readValue(finalPayload, TestPayload.class);
        System.out.println(testPayload);
        return new ResponseEntity(testPayload, HttpStatus.OK);
    }
}
