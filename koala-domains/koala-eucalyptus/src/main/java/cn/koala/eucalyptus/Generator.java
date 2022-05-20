package cn.koala.eucalyptus;

import java.util.Map;

/**
 * @author Houtaroy
 */
public interface Generator {
  /**
   * 生成代码
   *
   * @param domain  领域定义
   * @param options 选项
   * @return 生成结果
   * @throws Exception 异常
   */
  String generate(Domain domain, Map<String, Object> options) throws Exception;
}
