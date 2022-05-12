package com.pmp.server.annotation;

import com.pmp.server.annotation.validator.GenderValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {
  String message() default "Gender must be MALE, FEMALE OR OTHER";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
