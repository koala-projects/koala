package cn.koala.mybatis;

import lombok.Getter;

/**
 * 是否枚举
 *
 * @author Houtaroy
 */
@Getter
public enum YesNo implements EnhancedEnum {
  YES("是", 1),
  NO("否", 0);

  private final String name;
  private final int value;

  YesNo(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
