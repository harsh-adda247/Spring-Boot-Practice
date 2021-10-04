package com.example.demo.service.impl;

import com.example.demo.entities.QuestionEntity;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.requestModel.QuestionsRequestModel;
import com.example.demo.responseModel.QuestionResponseModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.QuestionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionRepository questionRepository;

    private static Logger logger = LoggerFactory.getLogger(QuestionsServiceImpl.class);

    /**
     * method to save a new question
     *
     * @param questionsRequestModel
     * @return
     */
    @Override
    public ResponseModel<QuestionResponseModel> saveQuestion(QuestionsRequestModel questionsRequestModel) {
        logger.info("**** Inside QuestionServiceImpl ==> saveQuestion ****");
        Map<QuestionEntity, List<String>> map = mapQuestionRequestIntoEntityAndSave(questionsRequestModel);
        if (map == null) {
            logger.info("The response came as null while saving the question");
            return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Couldn't save question due to bad request", null, null);
        }
        QuestionResponseModel response = null;
        Set<Map.Entry<QuestionEntity, List<String>>> entrySet = map.entrySet();
        for (Map.Entry<QuestionEntity, List<String>> mp : entrySet) {
            QuestionEntity questionEntity = this.questionRepository.save(mp.getKey());
            response = prepareQuestionResponse(questionEntity.getQuestion(), questionEntity.getAnswer(), mp.getValue());
            break;
        }
        logger.info("questions saved successfully");
        return new ResponseModel<QuestionResponseModel>(HttpStatus.OK, "Question saved successfully", null, response);
    }

    /**
     * method to save a list of questions
     *
     * @param questionsRequestModels
     * @return
     */
    @Override
    public ResponseModel<List<QuestionResponseModel>> saveQuestions(List<QuestionsRequestModel> questionsRequestModels) {
        logger.info("**** Inside QuestionServiceImpl ==> saveQuestions() ****");
        List<QuestionEntity> questionEntities = new ArrayList<>();
        List<QuestionResponseModel> questionsResponses = new ArrayList<>();
        for (QuestionsRequestModel question : questionsRequestModels) {
            Map<QuestionEntity, List<String>> map = mapQuestionRequestIntoEntityAndSave(question);
            if (map == null) {
                logger.info("The response came as null while saving the question");
                return new ResponseModel<>(HttpStatus.BAD_REQUEST, "Couldn't save question due to bad request", null, null);
            }
            QuestionResponseModel response = null;
            Set<Map.Entry<QuestionEntity, List<String>>> entrySet = map.entrySet();
            for (Map.Entry<QuestionEntity, List<String>> mp : entrySet) {
                questionEntities.add(mp.getKey());
                response = prepareQuestionResponse(mp.getKey().getQuestion(), mp.getKey().getAnswer(), mp.getValue());
            }
            questionsResponses.add(response);
        }
        this.questionRepository.saveAll(questionEntities);
        logger.info("list of questions saved successfully");
        return new ResponseModel<List<QuestionResponseModel>>(HttpStatus.OK, "Questions saved successfully", null, questionsResponses);
    }

    /**
     * method to extract options for each individual question
     *
     * @param questionsRequestModel
     * @param list
     * @return
     */
    private static List<String> generateOptions(QuestionsRequestModel questionsRequestModel, List<String> list) {
        Set<Map.Entry<String, Boolean>> entrySet = questionsRequestModel.getOptions().entrySet();
        for (Map.Entry<String, Boolean> map : entrySet) {
            list.add(map.getKey());
        }
        return list;
    }

    /**
     * method to generate response for questions being saved
     *
     * @param question
     * @param answer
     * @param options
     * @return
     */
    private QuestionResponseModel prepareQuestionResponse(String question, String answer, List<String> options) {
        QuestionResponseModel questionResponseModel = new QuestionResponseModel();
        questionResponseModel.setQuestionl(question);
        questionResponseModel.setOptions(options);
        questionResponseModel.setAnswer(answer);
        return questionResponseModel;
    }

    /**
     * method to update and return questionId count via using file mechanism
     *
     * @return
     */
    private static int updateQuestionIdCount() {
        String directoryPath = FileSystemView.getFileSystemView().
                getDefaultDirectory() + "/QuestionId.txt";
        File questionIdFile = new File(directoryPath);
        try {
            return readFromAndWriteIntoFile(questionIdFile);
        } catch (Exception e) {
            logger.error("Error occurred while updating questionId from file QuestionId.txt " + e.getMessage());
            return -1;
        }
    }

    /**
     * method to read data from the file instance and update the values in it accordingly
     * and if not found then initialise the starting questionId as 1
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static int readFromAndWriteIntoFile(File file) throws Exception {
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            String data = sc.nextLine();
            sc.close();
            int lastQuestionId = Integer.parseInt(data);
            int newQuestionId = lastQuestionId + 1;
            return writeIntoFile(file, newQuestionId);
        }

        file.createNewFile();
        return writeIntoFile(file, 1);
    }

    /**
     * method to write the provided value into the provided file instance
     *
     * @param file
     * @param questionId
     * @return
     * @throws Exception
     */
    private static int writeIntoFile(File file, int questionId) throws Exception {
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(file);         //to clear (delete) all the existing file contents
        pw.close();
        fw.write(String.valueOf(questionId));
        fw.close();
        return questionId;
    }

    /**
     * method to map question request model into question entity
     *
     * @param questionsRequestModel
     * @return
     */
    private Map<QuestionEntity, List<String>> mapQuestionRequestIntoEntityAndSave(QuestionsRequestModel questionsRequestModel) {
        logger.info("Inside QuestionServiceImpl ==> mapping question request into entity");
        List<String> list = new ArrayList<>();
        Integer questionId = updateQuestionIdCount();
        if (questionId == -1) {
            logger.info("Exception occurred while reading QuestionId.txt file");
            return null;
        }
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestion(questionsRequestModel.getQuestion());
        list = generateOptions(questionsRequestModel, list);
        questionEntity.setOption1(list.get(0));
        questionEntity.setOption2(list.get(1));
        questionEntity.setOption3(list.get(2));
        questionEntity.setOption4(list.get(3));
        questionEntity.setExamId(questionsRequestModel.getExamId());
        questionEntity.setAnswer(questionsRequestModel.getAnswer());
        questionEntity.setQuestionId(questionId);
        Map<QuestionEntity, List<String>> map = new HashMap<>();
        map.put(questionEntity, list);
        return map;
    }
}
