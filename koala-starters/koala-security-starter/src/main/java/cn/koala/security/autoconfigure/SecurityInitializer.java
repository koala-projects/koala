package cn.koala.security.autoconfigure;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 安全模块初始化器
 *
 * @author Houtaroy
 */
public class SecurityInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "security";
  }

  @Override
  public int getOrder() {
    return 4250;
  }
}
