package cn.koala.postoffice.support;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;

/**
 * 微信小程序订阅消息驿站
 *
 * @author Houtaroy
 */
public class WechatMiniappPostOffice extends AbstractObjectMapperPostOffice<WxMaSubscribeMessage> {

  public WechatMiniappPostOffice(WxMaService wxMaService) {
    super("wechat-miniapp", WxMaSubscribeMessage.class, wxMaService.getSubscribeService()::sendSubscribeMsg);
  }
}
