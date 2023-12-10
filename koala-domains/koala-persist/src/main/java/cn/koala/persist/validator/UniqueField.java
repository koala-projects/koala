package cn.koala.persist.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 唯一字段校验注解
 *
 * @author Houtaroy
 */
@Deprecated
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueFieldValidator.class)
@Repeatable(UniqueField.List.class)
public @interface UniqueField {
  String DEFAULT_MESSAGE_SUFFIX = "{persist.validator.unique}";

  /**
   * 字段名称
   *
   * @return 字段名称
   */
  String value();

  /**
   * 字段对应的查询参数名称
   *
   * @return 字段对应的查询参数名称
   */
  String parameter() default "";

  String message() default DEFAULT_MESSAGE_SUFFIX;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  @Target(ElementType.TYPE)
  @Retention(RUNTIME)
  @Documented
  @interface List {
    UniqueField[] value();
  }
}
