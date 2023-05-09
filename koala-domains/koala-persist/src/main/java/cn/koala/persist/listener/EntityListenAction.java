package cn.koala.persist.listener;

import cn.koala.persist.CrudType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体监听行为
 *
 * @author Houtaroy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EntityListenAction {

  /**
   * CRUD操作类型
   *
   * @return CRUD操作类型
   */
  CrudType value();

  Class<?> entity() default Void.class;

  /**
   * 是否开启前置操作
   *
   * @return 布尔值
   */
  boolean pre() default true;

  /**
   * 是否后置前置操作
   *
   * @return 布尔值
   */
  boolean post() default true;
}
