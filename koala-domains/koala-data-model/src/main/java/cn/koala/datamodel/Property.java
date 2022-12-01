package cn.koala.datamodel;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;

/**
 * 属性
 *
 * @author Houtaroy
 */
public interface Property extends Idable<String>, Codeable {
  /**
   * 获取属性类型
   *
   * @return 属性类型
   */
  String getType();

  /**
   * 获取所属元数据
   *
   * @return 元数据
   */
  Metadata getMetadata();
}
