package cn.koala.database;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 数据库模块初始化器
 *
 * @author Houtaroy
 */
public class DatabaseInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "database";
  }

  @Override
  public int getOrder() {
    return 4230;
  }
}
