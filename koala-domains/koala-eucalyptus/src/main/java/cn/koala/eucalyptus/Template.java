package cn.koala.eucalyptus;

import java.util.Map;

/**
 * @author Houtaroy
 */
public interface Template {
  /**
   * 获取模板名称
   *
   * @return 模板名称
   */
  String getName();

  /**
   * 获取模板编码
   *
   * @return 模板编码
   */
  String getCode();

  /**
   * 生成代码
   *
   * @param context 领域上下文
   * @return 代码
   */
  Map<String, String> process(DomainContext context);
}
