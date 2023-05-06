package cn.koala.persist.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 唯一校验注解
 *
 * @author Houtaroy
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface Unique {

  String[] fields();

  String message() default "{system.unique}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
