package cn.koala.admin.server.strategy.support;

import cn.koala.admin.server.Maintainer;
import cn.koala.admin.server.MessageFactory;
import cn.koala.admin.server.support.SimpleStatusChangeMessageFactory;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;

/**
 * 企业微信通知策略
 *
 * @author Houtaroy
 */
@Slf4j
public class WeworkNotifyStrategy extends AbstractNotifyStrategy {

  private final WxCpService wxCpService;

  public WeworkNotifyStrategy(WxCpService wxCpService) {
    this(wxCpService, new SimpleStatusChangeMessageFactory());
  }

  public WeworkNotifyStrategy(WxCpService wxCpService, MessageFactory messageFactory) {
    super(messageFactory);
    this.wxCpService = wxCpService;
  }

  @Override
  public String getName() {
    return "wework";
  }

  @Override
  protected boolean doNotify(Maintainer maintainer, String message) {
    try {
      WxCpMessage wxCpMessage = new WxCpMessage();
      wxCpMessage.setToUser(maintainer.getUsername());
      wxCpMessage.setMsgType("text");
      wxCpMessage.setContent(message);
      wxCpService.getMessageService().send(wxCpMessage);
      return true;
    } catch (WxErrorException e) {
      LOGGER.error("[koala-admin-server]: 发送企业微信消息[{}]给[{}]失败", message, maintainer.getUsername(), e);
      return false;
    }
  }
}
