package cn.koala.code.autoconfigure;

import cn.koala.code.apis.CodeApi;
import cn.koala.code.apis.CodeApiImpl;
import cn.koala.code.processors.ContextProcessor;
import cn.koala.code.processors.DelegatingContextProcessor;
import cn.koala.code.processors.StaticProcessor;
import cn.koala.code.processors.TableProcessor;
import cn.koala.code.processors.java.ApiProcessor;
import cn.koala.code.processors.java.DomainNameProcessor;
import cn.koala.code.processors.java.EntityProcessor;
import cn.koala.code.processors.java.MyBatisProcessor;
import cn.koala.code.services.CodeService;
import cn.koala.code.services.KoalaCodeService;
import cn.koala.database.services.DatabaseService;
import cn.koala.template.services.TemplateGroupService;
import cn.koala.template.services.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 代码自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(CodeProperties.class)
@Import(WebMvcConfig.class)
@RequiredArgsConstructor
public class CodeAutoConfiguration {

  protected final CodeProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public ContextProcessor contextProcessor() {
    return new DelegatingContextProcessor(
      new StaticProcessor("package", properties.getPackageName()),
      new TableProcessor(),
      new DomainNameProcessor(properties.getTablePrefix()),
      new ApiProcessor(properties.getTablePrefix()),
      new EntityProcessor(),
      new MyBatisProcessor()
    );
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeService codeService(ContextProcessor processor) {
    return new KoalaCodeService(processor, properties.getDownloadPath());
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeApi codeApi(DatabaseService databaseService, TemplateGroupService templateGroupService,
                         TemplateService templateService, CodeService codeService) {
    return new CodeApiImpl(databaseService, templateGroupService, templateService, codeService);
  }
}
