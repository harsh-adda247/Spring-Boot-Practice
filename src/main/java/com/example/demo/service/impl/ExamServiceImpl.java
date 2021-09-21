package com.example.demo.service.impl;

import com.example.demo.entities.ExamEntity;
import com.example.demo.repository.ExamRepository;
import com.example.demo.requestModel.ExamRequestModel;
import com.example.demo.responseModel.ResponseModel;
import com.example.demo.service.ExamService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    private static ModelMapper modelMapper = new ModelMapper();

    private static Logger logger = LoggerFactory.getLogger(ExamServiceImpl.class);

    public ExamServiceImpl() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    /**
     * method to save a new exam
     *
     * @param examRequestModel
     * @return
     */
    @Override
    public ResponseModel<?> saveExam(ExamRequestModel examRequestModel) {
        logger.info("**** Inside ExamServiceImpl ==> saveExam ****");
        Integer examId = updateExamIdCount();
        if (examId == -1) {
            return new ResponseModel<>(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unexpected server error occurred, sorry for the inconvenience !",
                    null, null);
        }
        examRequestModel.setExamId(examId);
        ExamEntity examEntity = modelMapper.map(examRequestModel, ExamEntity.class);
        examEntity = examRepository.save(examEntity);
        logger.info("exam saved successfully");
        return new ResponseModel<>(HttpStatus.OK, "exam saved successfully for examId : "
                + examEntity.getExamId(), null, null);
    }

    /**
     * method to update and return examId count via using file mechanism
     *
     * @return
     */
    private static int updateExamIdCount() {
        String directoryPath = FileSystemView.getFileSystemView().
                getDefaultDirectory() + "/Documents/ExamId.txt";
        File examIdFile = new File(directoryPath);
        try {
            return readFromAndWriteIntoFile(examIdFile);
        } catch (Exception e) {
            logger.error("Error occurred while updating examId from file ExamId.txt " + e.getMessage());
            return -1;
        }
    }

    /**
     * method to read data from the file instance and update the values in it accordingly
     * and if not found then initialise the starting examId as 1
     *
     * @param file
     * @return
     * @throws Exception
     */
    private static int readFromAndWriteIntoFile(File file) throws Exception {
        if (file.exists()) {
            Scanner sc = new Scanner(file);
            String data = null;
            while (sc.hasNext()) {
                data = sc.nextLine();
            }
            sc.close();
            int lastExamId = Integer.parseInt(data);
            int newExamId = lastExamId + 1;
            return writeIntoFile(file, newExamId);
        }

        file.createNewFile();
        return writeIntoFile(file, 1);
    }

    /**
     * method to write the provided value into the provided file instance
     *
     * @param file
     * @param examId
     * @return
     * @throws Exception
     */
    private static int writeIntoFile(File file, int examId) throws Exception {
        FileWriter fw = new FileWriter(file, true);
        fw.write(String.valueOf(examId + "\n"));
        fw.append(String.valueOf(""));
        fw.close();
        return examId;
    }
}
