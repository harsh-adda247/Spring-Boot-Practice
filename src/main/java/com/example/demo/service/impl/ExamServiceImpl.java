package com.example.demo.service.impl;

import com.example.demo.entities.ExamEntity;
import com.example.demo.repository.ExamRepository;
import com.example.demo.requestModel.ExamRequestModel;
import com.example.demo.requestModel.QuestionsRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.ExamService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    private static Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);

    public ExamServiceImpl() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public ResponseModel<?> saveExam(ExamRequestModel examRequestModel) {
        logger.info("**** Inside ExamServiceImpl ==> saveExam ****");
        ExamEntity examEntity = modelMapper.map(examRequestModel, ExamEntity.class);
        examEntity = examRepository.save(examEntity);
        Optional<ExamEntity> entity = examRepository.findById(examEntity.getId());
        if(entity != null) {
            examEntity = entity.get();
        }
        logger.info("exam saved successfully");
        return new ResponseModel<>(HttpStatus.OK, "exam saved successfully for examId : "+examEntity.getExamId(), null, null);
    }
}
