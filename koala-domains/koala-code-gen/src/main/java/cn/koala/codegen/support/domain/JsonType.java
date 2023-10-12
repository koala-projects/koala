package cn.koala.codegen.support.domain;

import cn.koala.persist.domain.EnumAdvice;
import lombok.Getter;

/**
 * Java类型
 *
 * @author Houtaroy
 */
@Getter
public enum JsonType implements EnumAdvice {

  INTEGER("integer", 1),
  NUMBER("number", 2),
  BOOLEAN("boolean", 3),
  STRING("string", 4),
  TIME("time", 5),
  DATE_TIME("date-time", 6);

  private final String name;
  private final int value;

  JsonType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
