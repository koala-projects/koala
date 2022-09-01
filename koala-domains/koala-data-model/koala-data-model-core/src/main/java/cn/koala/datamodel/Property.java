package cn.koala.datamodel;

import cn.koala.persistence.Codeable;

/**
 * 属性
 *
 * @author Houtaroy
 */
public interface Property extends Codeable {

  /**
   * 获取属性类型
   *
   * @return 属性类型
   */
  PropertyType getType();

  /**
   * 获取元数据
   *
   * @return 元数据
   */
  MetaData getMetaData();
}
