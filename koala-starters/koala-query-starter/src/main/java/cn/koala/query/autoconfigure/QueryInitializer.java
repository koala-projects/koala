package cn.koala.query.autoconfigure;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 安全模块初始化器
 *
 * @author Houtaroy
 */
public class QueryInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "query";
  }

  @Override
  public int getOrder() {
    return 4290;
  }
}
