package com.example.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OptionsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOptions {

    String message() default "Invalid options - options in total must be 4 and one of them should be the answer";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
