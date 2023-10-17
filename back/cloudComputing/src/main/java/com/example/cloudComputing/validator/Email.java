package com.example.cloudComputing.validator;

//import jakarta.validation.Constraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EmailValidation.class})

public @interface Email {

  String message() default "The email address is not valid!";

  //represents group of constraints
  Class<?>[] groups() default {};

  //represents additional information about annotation
  Class<? extends Payload>[] payload() default {};
}
