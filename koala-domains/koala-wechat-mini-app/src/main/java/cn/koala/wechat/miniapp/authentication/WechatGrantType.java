package cn.koala.wechat.miniapp.authentication;

import org.springframework.security.oauth2.core.AuthorizationGrantType;

/**
 * 微信授权类型
 *
 * @author Houtaroy
 */
public class WechatGrantType {
  public static final AuthorizationGrantType MINI_APP = new AuthorizationGrantType("wechat-mini-app");
}
