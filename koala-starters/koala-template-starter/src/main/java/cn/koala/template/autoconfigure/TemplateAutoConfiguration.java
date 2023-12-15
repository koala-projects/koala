package cn.koala.template.autoconfigure;

import cn.koala.template.api.DefaultTemplateApi;
import cn.koala.template.api.DefaultTemplateGroupApi;
import cn.koala.template.api.TemplateApi;
import cn.koala.template.api.TemplateGroupApi;
import cn.koala.template.domain.EnjoyTemplateRenderer;
import cn.koala.template.domain.TemplateRenderer;
import cn.koala.template.repository.TemplateGroupRepository;
import cn.koala.template.repository.TemplateRepository;
import cn.koala.template.service.DefaultTemplateGroupService;
import cn.koala.template.service.DefaultTemplateService;
import cn.koala.template.service.TemplateGroupService;
import cn.koala.template.service.TemplateService;
import com.jfinal.template.Engine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库自动配置类
 *
 * @author Houtaroy
 */
@MapperScan("cn.koala.template.repository")
@Configuration
public class TemplateAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public TemplateGroupService templateGroupService(TemplateGroupRepository templateGroupRepository) {
    return new DefaultTemplateGroupService(templateGroupRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public TemplateGroupApi templateGroupApi(TemplateGroupService templateGroupService) {
    return new DefaultTemplateGroupApi(templateGroupService);
  }

  @Bean
  @ConditionalOnMissingBean
  public TemplateService templateService(TemplateRepository templateRepository) {
    return new DefaultTemplateService(templateRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnClass(Engine.class)
  public TemplateRenderer templateRenderer() {
    return new EnjoyTemplateRenderer();
  }

  @Bean
  @ConditionalOnMissingBean
  public TemplateApi templateApi(TemplateService templateService, TemplateRenderer templateRenderer) {
    return new DefaultTemplateApi(templateService, templateRenderer);
  }
}
