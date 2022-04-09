package cn.houtaroy.koala.domain.models;

import java.io.Serializable;

/**
 * @author Houtaroy
 */
public interface ValueNameEnum extends Serializable {

  /**
   * 获取枚举值
   *
   * @return 枚举值
   */
  int getValue();

  /**
   * 获取枚举名称
   *
   * @return 枚举名称
   */
  String getName();

  /**
   * 将整形的值转换为指定类型的枚举
   *
   * @param enumClass 枚举类型
   * @param value     值
   * @param <E>       泛型
   * @return 值对应的指定类型的枚举值
   */
  static <E extends ValueNameEnum> E valueOf(Class<E> enumClass, int value) {
    E[] enumConstants = enumClass.getEnumConstants();
    for (E e : enumConstants) {
      if (e.getValue() == value) {
        return e;
      }
    }
    return null;
  }
}
