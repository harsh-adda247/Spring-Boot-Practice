package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE })
@Constraint(validatedBy = OptionsValidator.class)
public @interface ValidOptions {

    String message() default "{Invalid options}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
