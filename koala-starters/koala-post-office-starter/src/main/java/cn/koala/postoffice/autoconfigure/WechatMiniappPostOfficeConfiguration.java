package cn.koala.postoffice.autoconfigure;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.koala.postoffice.PostOffice;
import cn.koala.postoffice.support.WechatMiniappPostOffice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信小程序驿站配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "cn.binarywang.wx.miniapp.api.WxMaService")
public class WechatMiniappPostOfficeConfiguration {

  @Bean
  @ConditionalOnBean(WxMaService.class)
  public PostOffice weworkPostOffice(WxMaService wxMaService) {
    return new WechatMiniappPostOffice(wxMaService);
  }
}
