package com.example.demo.requestModel;

import com.example.demo.validation.ValidOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionsRequestModel {

    @Null(message = "id not allowed")
    private Integer id;

    @NotNull(message = "exam id can't be missing or empty")
    private Integer examId;

    @NotBlank(message = "question can't be missing or empty")
    private String question;

    @ValidOptions
    private Map<String, Boolean> options;

    @Null(message = "answer isn't allowed")
    private String answer;

    public QuestionsRequestModel identifyAnswer(QuestionsRequestModel questionsRequestModel) {
        Set<Map.Entry<String, Boolean>> entrySet = questionsRequestModel.getOptions().entrySet();
        for(Map.Entry<String, Boolean> entry: entrySet){
            if(entry.getValue() == true) {
                questionsRequestModel.setAnswer(entry.getKey());
                break;
            }
        }
        return questionsRequestModel;
    }
}
