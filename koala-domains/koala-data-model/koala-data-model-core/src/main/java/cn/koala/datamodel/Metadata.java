package cn.koala.datamodel;

import cn.koala.persistence.Idable;

import java.util.List;

/**
 * 元数据
 *
 * @author Houtaroy
 */
public interface Metadata extends Idable<String> {
  /**
   * 获取元数据名称
   *
   * @return 元数据名称
   */
  String getName();

  /**
   * 获取属性列表
   *
   * @param <T> 属性类型
   * @return 属性列表
   */
  <T extends Property> List<T> getProperties();
}
