package com.example.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.constraints.Size;

@Component
public class PhoneNumber implements Validator {
    @Size(min = 2, max = 30, message = "Độ dài cho phép 2->30 ký tự thui nha!")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PhoneNumber.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PhoneNumber phoneNumber = (PhoneNumber) target;
        String number = phoneNumber.getNumber();
        ValidationUtils.rejectIfEmpty(errors,"number","number.empty");
        if(!number.matches("^$|[0-9]*$")){
            errors.rejectValue("number","number.matches");
        }
    }
}
