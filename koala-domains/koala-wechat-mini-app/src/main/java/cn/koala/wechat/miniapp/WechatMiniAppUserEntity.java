package cn.koala.wechat.miniapp;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 微信小程序用户实体类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class WechatMiniAppUserEntity implements WechatMiniAppUser {
  private Long id;
  private String openid;
  private String unionid;
  private String sessionKey;
  private Long userId;

  public static WechatMiniAppUserEntity from(WxMaJscode2SessionResult code2SessionResult) {
    return WechatMiniAppUserEntity.builder()
      .openid(code2SessionResult.getOpenid())
      .unionid(code2SessionResult.getUnionid())
      .sessionKey(code2SessionResult.getSessionKey())
      .build();
  }
}
