package cn.koala.sensitiveword;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 替换注解
 *
 * @author Houtaroy
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Replacement {
  /**
   * 替换字符
   *
   * @return 替换字符
   */
  char value();
}
