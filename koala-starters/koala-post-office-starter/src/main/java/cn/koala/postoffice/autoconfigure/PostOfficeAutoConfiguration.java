package cn.koala.postoffice.autoconfigure;

import cn.koala.postoffice.PostOffice;
import cn.koala.postoffice.PostOfficeApi;
import cn.koala.postoffice.PostOfficeService;
import cn.koala.postoffice.support.DefaultPostOfficeApi;
import cn.koala.postoffice.support.InMemoryPostOfficeService;
import com.binarywang.spring.starter.wxjava.cp.config.WxCpAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * 驿站自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@AutoConfigureAfter({WxCpAutoConfiguration.class, MailSenderAutoConfiguration.class})
@Import({SimpleMailPostOfficeConfiguration.class, AliyunSmsPostOfficeConfiguration.class, WeworkPostOfficeConfiguration.class})
public class PostOfficeAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public PostOfficeService postOfficeService(List<PostOffice> postOffices) {
    return new InMemoryPostOfficeService(postOffices);
  }

  @Bean
  @ConditionalOnMissingBean
  public PostOfficeApi postOfficeApi(PostOfficeService postOfficeService) {
    return new DefaultPostOfficeApi(postOfficeService);
  }
}
