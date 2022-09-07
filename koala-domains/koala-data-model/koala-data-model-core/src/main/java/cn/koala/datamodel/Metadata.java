package cn.koala.datamodel;

import cn.koala.persistence.Codeable;

import java.util.List;

/**
 * 元数据
 *
 * @author Houtaroy
 */
public interface Metadata extends Codeable {
  /**
   * 获取属性列表
   *
   * @param <T> 属性类型
   * @return 属性列表
   */
  <T extends Property> List<T> getProperties();
}
