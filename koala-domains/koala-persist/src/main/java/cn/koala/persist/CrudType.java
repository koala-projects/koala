package cn.koala.persist;

import cn.koala.persist.domain.EnumAdvice;
import lombok.Getter;

/**
 * 增删改查类型
 *
 * @author Houtaroy
 */
@Deprecated
@Getter
public enum CrudType implements EnumAdvice {

  PAGE("分页", 1),
  LIST("列表", 2),
  LOAD("查看", 3),
  CREATE("新增", 4),
  UPDATE("修改", 5),
  DELETE("删除", 6),
  UNDEFINED("未定义", -1);

  private final String name;
  private final int value;

  CrudType(String name, int value) {
    this.name = name;
    this.value = value;
  }
}
