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

  CrudType value();

  Class<?> entity() default Void.class;

  boolean pre() default true;

  boolean post() default true;
}
