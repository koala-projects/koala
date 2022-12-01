package cn.koala.lang;

import java.util.List;

/**
 * 类型帮助类
 *
 * @author Houtaroy
 */
public abstract class ClassHelper {
  /**
   * 数组类型转换
   *
   * @param list   原始数组
   * @param tClass 转换的类型
   * @param <T>    转换的类型
   * @return 转换为指定类型的数组
   */
  public static <T> List<T> cast(List<?> list, Class<T> tClass) {
    return list.stream().map(tClass::cast).toList();
  }
}
