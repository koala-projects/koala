package cn.koala.wechat.miniapp.domain;

import org.springframework.data.domain.Persistable;

/**
 * 微信小程序用户接口
 *
 * @author Houtaroy
 */
public interface WechatMiniAppUser extends Persistable<Long> {
  
  String getOpenid();

  void setOpenid(String openid);

  String getUnionid();

  void setUnionid(String unionid);

  String getSessionKey();

  void setSessionKey(String sessionKey);

  Long getUserId();

  void setUserId(Long userId);
}
