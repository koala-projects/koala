package cn.koala.postoffice.autoconfigure;

import cn.koala.postoffice.PostOffice;
import cn.koala.postoffice.support.WeworkPostOffice;
import me.chanjar.weixin.cp.api.WxCpService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 企业微信驿站配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "me.chanjar.weixin.cp.api.WxCpService")
public class WeworkPostOfficeConfiguration {

  @Bean
  @ConditionalOnBean(WxCpService.class)
  public PostOffice weworkPostOffice(WxCpService wxCpService) {
    return new WeworkPostOffice(wxCpService);
  }
}
