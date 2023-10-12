package cn.koala.code.autoconfigure;

import cn.koala.codegen.CodeGenApi;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.codegen.CodeGenService;
import cn.koala.codegen.CodeGenerator;
import cn.koala.codegen.CompositeCodeGenContextProcessor;
import cn.koala.codegen.support.DefaultCodeGenApi;
import cn.koala.codegen.support.DefaultCodeGenService;
import cn.koala.codegen.support.PackageCodeGenContextProcessor;
import cn.koala.codegen.support.SimpleCompositeCodeGenContextProcessor;
import cn.koala.codegen.support.TableCodeGenContextProcessor;
import cn.koala.codegen.support.TemplateEngineCodeGenerator;
import cn.koala.codegen.support.api.ApiCodeGenContextProcessor;
import cn.koala.codegen.support.domain.DomainCodeGenContextProcessor;
import cn.koala.codegen.support.entity.EntityCodeGenContextProcessor;
import cn.koala.database.services.DatabaseService;
import cn.koala.template.TemplateGroupService;
import cn.koala.template.TemplateRenderer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * 代码自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@Import(CodeGenWebMvcConfigurer.class)
@EnableConfigurationProperties(CodeGenProperties.class)
@RequiredArgsConstructor
public class CodeGenAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean(name = "tableCodeGenContextProcessor")
  public CodeGenContextProcessor tableCodeGenContextProcessor() {
    return new TableCodeGenContextProcessor();
  }

  @Bean
  @ConditionalOnMissingBean(name = "domainCodeGenContextProcessor")
  public CodeGenContextProcessor domainCodeGenContextProcessor(CodeGenProperties properties) {
    return new DomainCodeGenContextProcessor(properties.getTablePrefix());
  }

  @Bean
  @ConditionalOnMissingBean(name = "packageCodeGenContextProcessor")
  public CodeGenContextProcessor packageCodeGenContextProcessor(CodeGenProperties properties) {
    return new PackageCodeGenContextProcessor(properties.getPackageName());
  }

  @Bean
  @ConditionalOnMissingBean(name = "apiCodeGenContextProcessor")
  public CodeGenContextProcessor apiCodeGenContextProcessor() {
    return new ApiCodeGenContextProcessor();
  }

  @Bean
  @ConditionalOnMissingBean(name = "entityCodeGenContextProcessor")
  public CodeGenContextProcessor entityCodeGenContextProcessor() {
    return new EntityCodeGenContextProcessor();
  }

  @Bean
  @ConditionalOnMissingBean
  public CompositeCodeGenContextProcessor compositeCodeGenContextProcessor(List<CodeGenContextProcessor> processors) {
    return new SimpleCompositeCodeGenContextProcessor(processors);
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeGenerator codeGenerator(TemplateRenderer templateRenderer, CompositeCodeGenContextProcessor processor) {
    return new TemplateEngineCodeGenerator(templateRenderer, processor);
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeGenService codeGenService(DatabaseService databaseService, TemplateGroupService templateGroupService,
                                       CodeGenerator codeGenerator, CodeGenProperties properties) {

    return new DefaultCodeGenService(databaseService, templateGroupService, codeGenerator, properties.getDownloadPath());
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeGenApi codeApi(CodeGenService codeGenService) {
    return new DefaultCodeGenApi(codeGenService);
  }
}
