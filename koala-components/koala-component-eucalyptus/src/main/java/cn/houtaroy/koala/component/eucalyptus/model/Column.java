package cn.houtaroy.koala.component.eucalyptus.model;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
public interface Column {

  /**
   * 获取列名
   *
   * @return 列名
   */
  String getName();

  /**
   * 获取列类型
   *
   * @return 列类型
   */
  String getType();
}
