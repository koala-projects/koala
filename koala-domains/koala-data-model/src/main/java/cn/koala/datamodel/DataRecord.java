package cn.koala.datamodel;

import cn.koala.persistence.Idable;

/**
 * 数据记录
 *
 * @author Houtaroy
 */
public interface DataRecord extends Idable<String> {
  /**
   * 获取元数据
   *
   * @return 元数据
   */
  Metadata getMetadata();
}
