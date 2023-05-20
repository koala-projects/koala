package cn.koala.system;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 系统模块初始化器
 *
 * @author Houtaroy
 */
public class SystemInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "system";
  }

  @Override
  public int getOrder() {
    return 4260;
  }
}
