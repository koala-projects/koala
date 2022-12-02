package cn.koala.builder;

import cn.koala.builder.mybatis.CodeTemplateGroupRepository;
import cn.koala.builder.mybatis.CodeTemplateGroupServiceImpl;
import cn.koala.datamodel.PropertyService;
import cn.koala.datamodel.mybatis.PropertyRepository;
import cn.koala.datamodel.mybatis.PropertyServiceImpl;
import cn.koala.template.EnjoyRenderer;
import cn.koala.template.Renderer;
import cn.koala.template.TemplateService;
import cn.koala.template.mybatis.TemplateRepository;
import cn.koala.template.mybatis.TemplateServiceImpl;
import com.jfinal.template.Engine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = {"cn.koala.datamodel.mybatis", "cn.koala.template.mybatis", "cn.koala.builder.mybatis"})
public class BuilderAutoConfig {
  /**
   * 属性服务的bean
   *
   * @param propertyRepository 属性存储库对象
   * @return 属性服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public PropertyService propertyService(PropertyRepository propertyRepository) {
    return new PropertyServiceImpl(propertyRepository);
  }

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
   * 增强表领域转换器的bean
   *
   * @return 增强表领域转换器
   */
  @Bean
  @ConditionalOnMissingBean
  public DomainConverter enhancedTableDomainConverter() {
    return new EnhancedTableDomainConverter();
  }

  /**
   * 领域转换器服务的bean
   *
   * @param converters Spring中类型为领域转换器的bean列表
   * @return 领域转换器服务
   */
  @Bean
  @ConditionalOnMissingBean
  public DomainConverterService domainConverterService(List<DomainConverter> converters) {
    return new InMemoryDomainConverterService(converters);
  }

  /**
   * 代码模板组服务的bean
   *
   * @param codeTemplateGroupRepository 代码模板组存储库
   * @param propertyService             属性服务
   * @param templateService             模板服务
   * @param domainConverterService      领域转换器服务
   * @param renderer                    渲染器
   * @return 代码模板组服务
   */
  @Bean
  @ConditionalOnMissingBean
  public CodeTemplateGroupService codeTemplateGroupService(CodeTemplateGroupRepository codeTemplateGroupRepository,
                                                           PropertyService propertyService,
                                                           TemplateService templateService,
                                                           DomainConverterService domainConverterService,
                                                           Renderer renderer) {
    return new CodeTemplateGroupServiceImpl(codeTemplateGroupRepository, propertyService, templateService,
      domainConverterService, renderer);
  }

  /**
   * 代码模板组接口的bean
   *
   * @param codeTemplateGroupService 代码模板组服务
   * @return 代码模板组接口
   */
  @Bean
  @ConditionalOnMissingBean
  public CodeTemplateGroupApi codeTemplateGroupApi(CodeTemplateGroupService codeTemplateGroupService) {
    return new CodeTemplateGroupApiImpl(codeTemplateGroupService);
  }
}
