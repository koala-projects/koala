package cn.koala.codegen.support.domain;

import cn.koala.persist.domain.EnumAdvice;
import lombok.Getter;

/**
 * TypeScript类型
 *
 * @author Houtaroy
 */
@Getter
public enum VbenComponentType implements EnumAdvice {

  INPUT("Input", 1),
  INPUT_NUMBER("InputNumber", 2),
  SWITCH("Switch", 3);

  private final String name;
  private final int value;

  VbenComponentType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
