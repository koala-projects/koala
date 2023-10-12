package cn.koala.codegen.support.domain;

import cn.koala.persist.domain.EnumAdvice;
import lombok.Getter;

/**
 * TypeScript类型
 *
 * @author Houtaroy
 */
@Getter
public enum TypeScriptType implements EnumAdvice {

  NUMBER("number", 1),
  STRING("string", 2),
  BOOLEAN("boolean", 3);

  private final String name;
  private final int value;

  TypeScriptType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
