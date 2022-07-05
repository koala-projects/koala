package cn.koala.eucalyptus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@Configuration
public class EucalyptusAutoConfig {

  /**
   * 领域模型工厂的bean
   *
   * @return 领域模型工厂
   */
  @Bean
  public DomainFactory domainFactory() {
    return new JdbcDomainFactory("t_");
  }

  /**
   * 模板管理器的bean
   *
   * @param configuration 模板配置
   * @return 模板管理器
   */
  @Bean
  public TemplateManager templateManager(freemarker.template.Configuration configuration) {
    return new InMemoryTemplateManager(List.of(
      new FreemarkerTemplate(
        "mybatis",
        "MyBatis模板",
        configuration,
        Map.of(
          "mybatis/Api.ftl", "Api.java",
          "mybatis/ApiImpl.ftl", "ApiImpl.java",
          "mybatis/Entity.ftl", "Entity.java",
          "mybatis/Mapper.ftl", "Mapper.java",
          "mybatis/Repository.ftl", "Repository.java",
          "mybatis/Service.ftl", "Service.java"
        )
      )
    ));
  }
}
