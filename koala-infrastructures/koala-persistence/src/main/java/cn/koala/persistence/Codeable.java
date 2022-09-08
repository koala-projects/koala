package cn.koala.persistence;

/**
 * @author Houtaroy
 */
public interface Codeable {

  /**
   * 获取代码
   *
   * @return 代码
   */
  String getCode();

  /**
   * 设置代码
   *
   * @param code 代码
   */
  void setCode(String code);

  /**
   * 获取名称
   *
   * @return 名称
   */
  String getName();

  /**
   * 获取描述
   *
   * @return 描述
   */
  String getDescription();
}
