package cn.houtaroy.koala.component.eucalyptus.domain;

import java.util.Map;

/**
 * @author Houtaroy
 */
public interface Generator {
  /**
   * 生成代码
   *
   * @param domain 领域定义
   * @return 代码
   * @throws Exception 异常
   */
  Map<String, String> generate(Domain domain) throws Exception;
}
