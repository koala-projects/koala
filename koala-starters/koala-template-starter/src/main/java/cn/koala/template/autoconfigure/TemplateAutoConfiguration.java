package cn.koala.template.autoconfigure;

import cn.koala.template.TemplateApi;
import cn.koala.template.TemplateGroupApi;
import cn.koala.template.TemplateGroupService;
import cn.koala.template.TemplateRenderer;
import cn.koala.template.TemplateService;
import cn.koala.template.repository.TemplateGroupRepository;
import cn.koala.template.repository.TemplateRepository;
import cn.koala.template.support.DefaultTemplateApi;
import cn.koala.template.support.DefaultTemplateGroupApi;
import cn.koala.template.support.DefaultTemplateGroupService;
import cn.koala.template.support.DefaultTemplateService;
import cn.koala.template.support.EnjoyTemplateRenderer;
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
  public TemplateGroupService templateGroupService(TemplateGroupRepository templateGroupRepository,
                                                   TemplateService templateService) {
    return new DefaultTemplateGroupService(templateGroupRepository, templateService);
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
  @ConditionalOnClass(Engine.class)
  @ConditionalOnMissingBean
  public TemplateRenderer templateRenderer() {
    return new EnjoyTemplateRenderer();
  }

  @Bean
  @ConditionalOnMissingBean
  public TemplateApi templateApi(TemplateService templateService, TemplateRenderer templateRenderer) {
    return new DefaultTemplateApi(templateService, templateRenderer);
  }
}
