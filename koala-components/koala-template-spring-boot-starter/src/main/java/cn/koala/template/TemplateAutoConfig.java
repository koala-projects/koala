package cn.koala.template;

import cn.koala.template.mybatis.TemplateGroupRepository;
import cn.koala.template.mybatis.TemplateGroupServiceImpl;
import cn.koala.template.mybatis.TemplateRepository;
import cn.koala.template.mybatis.TemplateServiceImpl;
import com.jfinal.template.Engine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 模板自动配置
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan("cn.koala.template.mybatis")
public class TemplateAutoConfig {
  /**
   * 模板服务类的bean
   *
   * @param templateRepository 模板存储库类
   * @return 模板服务类
   */
  @Bean
  @ConditionalOnMissingBean
  public TemplateService templateService(TemplateRepository templateRepository) {
    return new TemplateServiceImpl(templateRepository);
  }

  /**
   * 模板组服务类的bean
   *
   * @param templateGroupRepository 模板组存储库类
   * @param templateService         模板服务对象
   * @return 模板组服务类
   */
  @Bean
  @ConditionalOnMissingBean
  public TemplateGroupService templateGroupService(TemplateGroupRepository templateGroupRepository,
                                                   TemplateService templateService) {
    return new TemplateGroupServiceImpl(templateGroupRepository, templateService);
  }

  /**
   * 渲染器的bean
   *
   * @return 渲染器对象
   */
  @Bean
  @ConditionalOnMissingBean
  public Renderer renderer() {
    return new EnjoyRenderer(Engine.create("_koala"));
  }

  /**
   * 模板接口的bean
   *
   * @param templateService 模板服务对象
   * @param renderer        渲染器
   * @return 模板接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public TemplateApi templateApi(TemplateService templateService, Renderer renderer) {
    return new TemplateApiImpl(templateService, renderer);
  }

  /**
   * 模板组接口的bean
   *
   * @param templateGroupService 模板组服务对象
   * @return 模板组接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public TemplateGroupApi templateGroupApi(TemplateGroupService templateGroupService) {
    return new TemplateGroupApiImpl(templateGroupService);
  }
}
