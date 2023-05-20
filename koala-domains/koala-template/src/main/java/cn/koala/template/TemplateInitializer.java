package cn.koala.template;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 模板模块初始化器
 *
 * @author Houtaroy
 */
public class TemplateInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "template";
  }

  @Override
  public int getOrder() {
    return 4270;
  }
}
