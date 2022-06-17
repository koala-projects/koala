package cn.koala.eucalyptus;

import java.util.Map;

/**
 * 领域上下文
 *
 * @author Houtaroy
 */
public interface DomainContext extends Map<String, Object> {
  /**
   * 获取领域模型
   *
   * @return 领域模型
   */
  Domain getDomain();
}
