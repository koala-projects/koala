package cn.koala.postoffice.autoconfigure;

import cn.koala.postoffice.PostOffice;
import cn.koala.postoffice.support.AliyunSmsPostOffice;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * 阿里云短信驿站配置类
 *
 * @author Houtaroy
 */
@Configuration
@ConditionalOnClass(name = "com.aliyun.dysmsapi20170525.Client")
@EnableConfigurationProperties(AliyunSmsPostOfficeProperties.class)
public class AliyunSmsPostOfficeConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public Client aliyunSmsClient(AliyunSmsPostOfficeProperties properties) throws Exception {
    Assert.notNull(properties, "请配置阿里云短信服务的AccessKeyId和AccessKeySecret");
    Assert.hasText(properties.getAccessKeyId(), "阿里云短信服务的AccessKeyId不能为空");
    Assert.hasText(properties.getAccessKeySecret(), "阿里云短信服务的AccessKeySecret不能为空");
    Assert.hasText(properties.getEndpoint(), "阿里云短信服务的endpoint不能为空");
    Config config = new Config();
    config.setAccessKeyId(properties.getAccessKeyId());
    config.setAccessKeySecret(properties.getAccessKeySecret());
    config.setEndpoint(properties.getEndpoint());
    return new Client(config);
  }

  @Bean
  @ConditionalOnBean(Client.class)
  @ConditionalOnMissingBean(name = "aliyunSmsPostOffice")
  public PostOffice aliyunSmsPostOffice(Client client) {
    return new AliyunSmsPostOffice(client);
  }
}
