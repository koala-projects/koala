package cn.koala.postoffice.support;

import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * 企业微信驿站
 *
 * @author Houtaroy
 */
public class WeworkPostOffice extends AbstractObjectMapperPostOffice<WxCpMessage> {

  public WeworkPostOffice(WxCpService wxCpService) {
    super("wework", WxCpMessage.class, wxCpService.getMessageService()::send);
  }
}
