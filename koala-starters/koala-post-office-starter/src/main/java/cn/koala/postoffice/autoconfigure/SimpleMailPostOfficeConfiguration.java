package cn.koala.postoffice.autoconfigure;

import cn.koala.postoffice.PostOffice;
import cn.koala.postoffice.support.SimpleMailPostOffice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

/**
 * 邮件驿站配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "org.springframework.mail.MailSender")
public class SimpleMailPostOfficeConfiguration {

  @Bean
  @ConditionalOnBean(MailSender.class)
  @ConditionalOnMissingBean(name = "simpleMailPostOffice")
  public PostOffice simpleMailPostOffice(MailSender mailSender) {
    return new SimpleMailPostOffice(mailSender);
  }
}
