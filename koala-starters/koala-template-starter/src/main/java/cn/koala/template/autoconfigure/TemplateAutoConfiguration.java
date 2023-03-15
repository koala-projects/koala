package cn.koala.template.autoconfigure;

import cn.koala.template.apis.TemplateGroupApi;
import cn.koala.template.apis.TemplateGroupApiImpl;
import cn.koala.template.repositories.TemplateGroupRepository;
import cn.koala.template.services.TemplateGroupService;
import cn.koala.template.services.TemplateGroupServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库自动配置类
 *
 * @author Houtaroy
 */
@MapperScan("cn.koala.template.repositories")
@Configuration
public class TemplateAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public TemplateGroupService templateGroupService(TemplateGroupRepository templateGroupRepository) {
    return new TemplateGroupServiceImpl(templateGroupRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public TemplateGroupApi templateGroupApi(TemplateGroupService templateGroupService) {
    return new TemplateGroupApiImpl(templateGroupService);
  }
}
