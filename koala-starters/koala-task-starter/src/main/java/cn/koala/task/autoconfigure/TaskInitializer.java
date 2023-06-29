package cn.koala.task.autoconfigure;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 任务模块初始化器
 *
 * @author Houtaroy
 */
public class TaskInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "task";
  }

  @Override
  public int getOrder() {
    return 4300;
  }
}
