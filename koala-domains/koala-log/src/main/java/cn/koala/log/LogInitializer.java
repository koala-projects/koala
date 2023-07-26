package cn.koala.log;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 日志模块初始化器
 *
 * @author Houtaroy
 */
public class LogInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "log";
  }

  @Override
  public int getOrder() {
    return 3040;
  }
}
