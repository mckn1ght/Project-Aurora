package com.example.cloudComputing.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PhoneNumberValidation.class})

public @interface PhoneNumber {

    String message() default "The phone number can have the following formats:\n" +
            "123-456-7890,\n" +
            "(123) 456-7890,\n" +
            "123 456 7890,\n" +
            "123.456.7890,\n" +
            "+91 (123) 456-7890,\n";

    //represents group of constraints
    Class<?>[] groups() default {};

    //represents additional information about annotation
    Class<? extends Payload>[] payload() default {};
}
