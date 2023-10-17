package com.example.cloudComputing.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidation implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
      String phoneNumberRegex = "^(\\+\\d{1,3}\\s?)?\\(?\\d{1,4}\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}\\s?((ext|x)\\s?\\d{1,5})?$";

      if(value == null)
            return false;
        return
          value.matches(phoneNumberRegex);
    }
}
