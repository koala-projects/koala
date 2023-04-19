package cn.koala.wechat.miniapp.registry;

import cn.koala.wechat.miniapp.WechatMiniAppUser;

/**
 * 微信小程序用户注册器
 *
 * @author Houtaroy
 */
public interface WechatMiniAppUserRegistry {
  void register(WechatMiniAppUser wechatMiniAppUser);
}
