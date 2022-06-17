package cn.koala.eucalyptus;

/**
 * 查询条件
 *
 * @author Houtaroy
 */
public interface SearchParameter {
  /**
   * 获取查询条件关联的领域模型属性
   *
   * @return 领域模型属性
   */
  DomainProperty getProperty();

  /**
   * 获取查询条件代码
   *
   * @return 代码
   */
  String getCode();

  /**
   * 获取查询条件名称
   *
   * @return 查询条件名称
   */
  String getName();

  /**
   * 获取查询条件类型
   *
   * @return 查询条件类型
   */
  String getType();

  /**
   * 获取查询条件操作符
   *
   * @return 查询条件操作符
   */
  Operator getOperator();
}
