package com.pmp.server.annotation.validator;

import com.pmp.server.annotation.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class GenderValidator implements ConstraintValidator<Gender, String> {
  private final List<String> genders = Arrays.asList("MALE", "FEMALE", "OTHER");

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

    return genders.contains(value);
  }
}
