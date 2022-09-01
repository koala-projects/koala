package cn.koala.datamodel;

import java.util.Map;

/**
 * @author Houtaroy
 */
public interface DataElement {
  /**
   * 获取数据元代码(属性代码)
   *
   * @return 数据元代码(属性代码)
   */
  String getCode();

  /**
   * 获取数据内容
   *
   * @return 数据内容
   */
  Object getContent();

  /**
   * 设置数据内容
   *
   * @param content 数据内容
   */
  void setContent(Object content);

  /**
   * 从所有数据中设置数据内容
   *
   * @param contents 所有数据
   */
  default void setContent(Map<String, Object> contents) {
    setContent(contents.get(getCode()));
  }

  /**
   * 获取所属数据
   *
   * @return 所属数据
   */
  Data getData();

  /**
   * 获取属性
   *
   * @return 属性
   */
  Property getProperty();
}
