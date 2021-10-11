package com.example.demo.validation;

import com.example.demo.requestModel.QuestionAnswerRequestModel;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class QuestionAnswersValidator implements ConstraintValidator<ValidQuestionAnswers, List<QuestionAnswerRequestModel>> {

    @Override
    public boolean isValid(List<QuestionAnswerRequestModel> questionAnswers, ConstraintValidatorContext context) {
        //Base Case
        if(questionAnswers == null || questionAnswers.size() == 0){
            return false;
        }
        for(QuestionAnswerRequestModel questionAnswer: questionAnswers){
            if(questionAnswer.getQuestionId() < 0 ||
                    StringUtils.isEmpty(questionAnswer.getAnswer())) return false;
        }
        return true;
    }
}
