package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy =QuestionAnswersValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidQuestionAnswers {

    String message() default "Invalid question - answer : Question id can't be negative or answer can't be missing or empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
