package cn.koala.code.autoconfigure;

import cn.koala.codegen.api.CodeGenApi;
import cn.koala.codegen.api.DefaultCodeGenApi;
import cn.koala.codegen.context.CodeGenContextProcessor;
import cn.koala.codegen.context.DomainCodeGenContextProcessor;
import cn.koala.codegen.context.JavaPackageCodeGenContextProcessor;
import cn.koala.codegen.context.KoalaApiCodeGenContextProcessor;
import cn.koala.codegen.context.KoalaEntityCodeGenContextProcessor;
import cn.koala.codegen.context.type.JdbcTypeMapping;
import cn.koala.codegen.context.validation.JakartaDigitsValidationBuilder;
import cn.koala.codegen.name.NameFactory;
import cn.koala.codegen.service.CodeGenService;
import cn.koala.codegen.service.DefaultCodeGenService;
import cn.koala.database.service.DatabaseService;
import cn.koala.template.domain.TemplateRenderer;
import cn.koala.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 代码自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(CodeGenProperties.class)
@RequiredArgsConstructor
public class CodeGenAutoConfiguration implements WebMvcConfigurer {

  private final CodeGenProperties properties;

  @Bean
  @ConditionalOnMissingBean(name = "packageCodeGenContextProcessor")
  public CodeGenContextProcessor packageCodeGenContextProcessor(CodeGenProperties properties) {
    return new JavaPackageCodeGenContextProcessor(properties.getPackageName());
  }

  @Bean
  @ConditionalOnMissingBean(name = "domainCodeGenContextProcessor")
  public CodeGenContextProcessor domainCodeGenContextProcessor(NameFactory nameFactory,
                                                               List<JdbcTypeMapping> typeMappings) {

    return new DomainCodeGenContextProcessor(
      properties.getTablePrefix(),
      properties.getTableRemarksSuffix(),
      nameFactory,
      typeMappings
    );
  }


  @Bean
  @ConditionalOnMissingBean(name = "koalaEntityCodeGenContextProcessor")
  public CodeGenContextProcessor koalaEntityCodeGenContextProcessor(
    List<JakartaDigitsValidationBuilder> validationBuilders) {

    return new KoalaEntityCodeGenContextProcessor(validationBuilders);
  }

  @Bean
  @ConditionalOnMissingBean(name = "koalaApiCodeGenContextProcessor")
  public CodeGenContextProcessor koalaApiCodeGenContextProcessor() {
    return new KoalaApiCodeGenContextProcessor();
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeGenService codeGenService(List<CodeGenContextProcessor> contextProcessors,
                                       TemplateRenderer templateRenderer) {

    return new DefaultCodeGenService(contextProcessors, templateRenderer);
  }

  @Bean
  @ConditionalOnMissingBean
  public CodeGenApi codeApi(DatabaseService databaseService, TemplateService templateService,
                            CodeGenService codeGenService, CodeGenProperties properties) {

    return new DefaultCodeGenApi(databaseService, templateService, codeGenService, properties.getPath());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/api/code-gen/file/**")
      .addResourceLocations("file:" + properties.getPath());
  }
}
