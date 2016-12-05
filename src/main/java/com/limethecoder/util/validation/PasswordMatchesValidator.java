package com.limethecoder.util.validation;


import com.limethecoder.data.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if(obj != null && obj instanceof UserDto) {
            UserDto user = (UserDto) obj;
            return user.getPassword().equals(user.getMatchingPassword());
        }

        return false;
    }
}