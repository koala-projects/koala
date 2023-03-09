package cn.koala.code.autoconfigure;

import cn.koala.code.CodeProperties;
import cn.koala.code.CodeService;
import cn.koala.code.EnjoyCodeService;
import cn.koala.code.apis.CodeApi;
import cn.koala.code.apis.CodeApiImpl;
import cn.koala.code.processors.ContextProcessor;
import cn.koala.code.processors.DelegatingContextProcessor;
import cn.koala.code.processors.DomainProcessor;
import cn.koala.code.processors.StaticProcessor;
import cn.koala.code.processors.TableProcessor;
import cn.koala.code.template.FileTemplateGroupService;
import cn.koala.code.template.TemplateGroupService;
import cn.koala.database.services.TableService;
import com.jfinal.template.Engine;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

/**
 * 代码生成器自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(CodeProperties.class)
@RequiredArgsConstructor
public class CodeAutoConfiguration {

  protected final CodeProperties properties;

  @Bean
  @ConditionalOnMissingBean
  public TemplateGroupService templateGroupService() {
    return new FileTemplateGroupService(new File(properties.getTemplatePath()));
  }

  @Bean
  @ConditionalOnMissingBean
  public ContextProcessor contextProcessor() {
    return new DelegatingContextProcessor(List.of(
      new StaticProcessor("package", properties.getPackageName()),
      new TableProcessor(),
      new DomainProcessor()
    ));
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeService codeService(ContextProcessor processor) {
    Engine engine = Engine.create("koala-code");
    engine.setBaseTemplatePath(properties.getTemplatePath());
    engine.setDevMode(true);
    return new EnjoyCodeService(engine, processor);
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeApi codeApi(TableService tableService, TemplateGroupService templateGroupService, CodeService codeService) {
    return new CodeApiImpl(tableService, templateGroupService, codeService);
  }
}
