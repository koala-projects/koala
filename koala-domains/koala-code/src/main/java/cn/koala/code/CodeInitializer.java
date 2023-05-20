package cn.koala.code;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 代码生成模块初始化器
 *
 * @author Houtaroy
 */
public class CodeInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "code";
  }

  @Override
  public int getOrder() {
    return 4220;
  }
}
