package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;
import java.util.Set;

public class OptionsValidator implements ConstraintValidator<ValidOptions, Map<String, Boolean>> {
    @Override
    public boolean isValid(Map<String, Boolean> options, ConstraintValidatorContext constraintValidatorContext) {
        //Base Case
        if(options.size() != 4) return false;
        int counter = 0;
        Set<Map.Entry<String, Boolean>> entrySet = options.entrySet();
        for(Map.Entry<String, Boolean> entry: entrySet){
            if(entry.getValue() == true) counter += 1;
        }
        if(counter == 1) return true;
        return false;
    }
}
