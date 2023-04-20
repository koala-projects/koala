package cn.koala.code.processors.java.converter;

import cn.koala.persist.domain.EnhancedEnum;
import lombok.Getter;

/**
 * Java类型
 *
 * @author Houtaroy
 */
@Getter
public enum JavaType implements EnhancedEnum {
  Integer("Integer", 1),
  Long("Long", 2),
  Float("Float", 3),
  Double("Double", 4),
  Boolean("Boolean", 5),
  String("String", 6),
  Time("Time", 7),
  Date("Date", 8);

  private final String name;
  private final int value;

  JavaType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
