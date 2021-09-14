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

    @NotBlank(message = "options can't be missing or empty")
    @Max(value = 4)
    @Min(value = 4)
    @ValidOptions
    private Map<String, Boolean> options;

    @Null(message = "answer isn't allowed")
    private String answer;

    public void identifyAnswer() {
        Set<Map.Entry<String, Boolean>> entrySet = this.options.entrySet();
        for(Map.Entry<String, Boolean> entry: entrySet){
            if(entry.getValue() == true) this.answer = entry.getKey(); break;
        }
    }
}
