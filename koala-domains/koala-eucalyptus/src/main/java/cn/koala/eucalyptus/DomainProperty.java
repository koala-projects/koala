package cn.koala.eucalyptus;

/**
 * 领域属性
 *
 * @author Houtaroy
 */
public interface DomainProperty {
  /**
   * 获取属性编码
   *
   * @return 属性编码
   */
  String getCode();

  /**
   * 获取属性名称
   *
   * @return 属性名称
   */
  String getName();

  /**
   * 获取属性类型
   *
   * @return 属性类型
   */
  String getType();
}
