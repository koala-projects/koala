package cn.koala.datamodel;

/**
 * 数据元
 *
 * @author Houtaroy
 */
public interface DataElement {
  /**
   * 获取数据内容
   *
   * @return 数据内容
   */
  Object getContent();

  /**
   * 获取属性
   *
   * @return 属性
   */
  Property getProperty();
}
