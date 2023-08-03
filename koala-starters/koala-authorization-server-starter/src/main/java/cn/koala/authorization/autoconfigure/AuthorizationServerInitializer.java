package cn.koala.authorization.autoconfigure;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 安全模块初始化器
 *
 * @author Houtaroy
 */
public class AuthorizationServerInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "authorization-server";
  }

  @Override
  public int getOrder() {
    return 3050;
  }
}
