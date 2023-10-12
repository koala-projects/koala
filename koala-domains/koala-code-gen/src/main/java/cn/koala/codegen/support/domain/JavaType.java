package cn.koala.codegen.support.domain;

import cn.koala.persist.domain.EnumAdvice;
import lombok.Getter;

/**
 * Java类型
 *
 * @author Houtaroy
 */
@Getter
public enum JavaType implements EnumAdvice {

  INTEGER("Integer", 1),
  LONG("Long", 2),
  FLOAT("Float", 3),
  DOUBLE("Double", 4),
  BOOLEAN("Boolean", 5),
  STRING("String", 6),
  TIME("Time", 7),
  DATE("Date", 8);

  private final String name;
  private final int value;

  JavaType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
