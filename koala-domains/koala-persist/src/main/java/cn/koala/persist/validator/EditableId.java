package cn.koala.persist.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可编辑数据ID校验注解
 *
 * @author Houtaroy
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EditableIdValidator.class)
public @interface EditableId {

  /**
   * 实体类型
   *
   * @return 实体类型
   */
  Class<?> value();

  String message() default "{persist.validator.editable}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
