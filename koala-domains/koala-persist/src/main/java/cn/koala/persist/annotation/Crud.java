package cn.koala.persist.annotation;

import cn.koala.persist.domain.EnumAdvice;
import lombok.Getter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 增删改查
 *
 * @author Houtaroy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Crud {
  Type type();

  @Getter
  enum Type implements EnumAdvice {

    ADD("新增", 1),
    UPDATE("修改", 2),
    DELETE("删除", 3);

    private final String name;
    private final int value;

    Type(String name, int value) {
      this.name = name;
      this.value = value;
    }
  }
}
