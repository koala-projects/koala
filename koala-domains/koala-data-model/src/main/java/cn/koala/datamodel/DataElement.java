package cn.koala.datamodel;

import cn.koala.persistence.Idable;

/**
 * 数据元
 *
 * @author Houtaroy
 */
public interface DataElement extends Idable<String> {
  /**
   * 获取数据元代码
   *
   * @return 数据元代码
   */
  String getCode();

  /**
   * 获取数据元内容
   *
   * @return 数据元内容
   */
  String getContent();

  /**
   * 获取对应属性
   *
   * @return 对应属性
   */
  Property getProperty();

  /**
   * 获取所属数据
   *
   * @return 数据
   */
  DataRecord getDataRecord();
}
