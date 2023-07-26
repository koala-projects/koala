package cn.koala.wechat.miniapp.support;

import cn.koala.persist.initializer.support.AbstractModuleInitializer;

/**
 * 微信小程序模块初始化器
 *
 * @author Houtaroy
 */
public class WechatMiniAppInitializer extends AbstractModuleInitializer {
  @Override
  public String getName() {
    return "wechat-mini-app";
  }

  @Override
  public int getOrder() {
    return 3080;
  }
}
