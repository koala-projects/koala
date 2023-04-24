package cn.koala.persist.domain;

import lombok.Getter;

/**
 * 是否枚举
 *
 * @author Houtaroy
 */
@Getter
public enum YesNo implements EnumAdvice {
  YES("是", 1),
  NO("否", 0);

  private final String name;
  private final int value;

  YesNo(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
