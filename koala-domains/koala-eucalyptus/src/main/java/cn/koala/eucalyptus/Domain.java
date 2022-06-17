package cn.koala.eucalyptus;

import java.util.List;

/**
 * 领域模型
 *
 * @author Houtaroy
 */
public interface Domain {
  /**
   * 获取领域编码
   *
   * @return 领域编码
   */
  String getCode();

  /**
   * 获取领域名称
   *
   * @return 领域名称
   */
  String getName();

  /**
   * 获取属性列表
   *
   * @return 属性列表
   */
  List<? extends DomainProperty> getProperties();
}
