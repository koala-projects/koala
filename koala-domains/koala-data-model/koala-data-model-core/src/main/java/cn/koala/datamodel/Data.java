package cn.koala.datamodel;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface Data {
  /**
   * 获取元数据
   *
   * @return 元数据
   */
  MetaData getMetaData();

  /**
   * 获取数据元列表
   *
   * @param <T> 数据元类型
   * @return 数据元列表
   */
  <T extends DataElement> List<T> getElements();
}
