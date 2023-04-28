package cn.koala.persist;

import cn.koala.persist.domain.EnumAdvice;
import lombok.Getter;

/**
 * 增删改查类型
 *
 * @author Houtaroy
 */
@Getter
public enum CrudType implements EnumAdvice {

  ADD("新增", 1),
  UPDATE("修改", 2),
  DELETE("删除", 3);

  private final String name;
  private final int value;

  CrudType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
