package cn.koala.code.autoconfigure;

import cn.koala.codegen.CodeGenApi;
import cn.koala.codegen.CodeGenContextProcessor;
import cn.koala.codegen.CodeGenService;
import cn.koala.codegen.CodeGenerator;
import cn.koala.codegen.CompositeCodeGenContextProcessor;
import cn.koala.codegen.name.AppendPluralConverter;
import cn.koala.codegen.name.NameFactory;
import cn.koala.codegen.name.NameStyleFactory;
import cn.koala.codegen.name.PluralConverter;
import cn.koala.codegen.name.PluralService;
import cn.koala.codegen.name.ReplacePluralConverter;
import cn.koala.codegen.name.SimplePluralService;
import cn.koala.codegen.name.StaticPluralConverter;
import cn.koala.codegen.support.DefaultCodeGenApi;
import cn.koala.codegen.support.DefaultCodeGenService;
import cn.koala.codegen.support.PackageCodeGenContextProcessor;
import cn.koala.codegen.support.SimpleCompositeCodeGenContextProcessor;
import cn.koala.codegen.support.TableCodeGenContextProcessor;
import cn.koala.codegen.support.TemplateEngineCodeGenerator;
import cn.koala.codegen.support.api.ApiCodeGenContextProcessor;
import cn.koala.codegen.support.domain.DomainCodeGenContextProcessor;
import cn.koala.codegen.support.entity.EntityCodeGenContextProcessor;
import cn.koala.database.service.DatabaseService;
import cn.koala.template.TemplateGroupService;
import cn.koala.template.TemplateRenderer;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
  @ConditionalOnMissingBean(name = "staticPluralConverter")
  public PluralConverter staticPluralConverter() {
    Map<String, String> statics = Maps.newHashMap();
    statics.put("child", "children");
    statics.put("Chinese", "Chinese");
    statics.put("deer", "deer");
    statics.put("foot", "feet");
    statics.put("goose", "geese");
    statics.put("Japanese", "Japanese");
    statics.put("man", "men");
    statics.put("mouse", "mice");
    statics.put("person", "people");
    statics.put("sheep", "sheep");
    statics.put("tooth", "teeth");
    statics.put("woman", "women");
    return new StaticPluralConverter(statics);
  }

  @Bean
  @ConditionalOnMissingBean(name = "vesReplacePluralConverter")
  public PluralConverter vesReplacePluralConverter() {
    return new ReplacePluralConverter(Pattern.compile("(f|fe)$", Pattern.CASE_INSENSITIVE), "ves");
  }

  @Bean
  @ConditionalOnMissingBean(name = "iesReplacePluralConverter")
  public PluralConverter iesReplacePluralConverter() {
    return new ReplacePluralConverter(
      Pattern.compile("([^aeiou])y$", Pattern.CASE_INSENSITIVE),
      "$1ies"
    );
  }

  @Bean
  @ConditionalOnMissingBean(name = "esAppendPluralConverter")
  public PluralConverter esAppendPluralConverter() {
    return new AppendPluralConverter(Pattern.compile("(s|sh|ch|x)$", Pattern.CASE_INSENSITIVE), "es");
  }

  @Bean
  @ConditionalOnMissingBean(name = "sAppendPluralConverter")
  public PluralConverter sAppendPluralConverter() {
    return new AppendPluralConverter(Pattern.compile("$", Pattern.CASE_INSENSITIVE), "s");
  }

  @Bean
  @ConditionalOnMissingBean
  public PluralService pluralService(List<PluralConverter> converters) {
    return new SimplePluralService(converters);
  }

  @Bean
  @ConditionalOnMissingBean
  public NameFactory nameFactory(PluralService pluralService) {
    return new NameFactory(new NameStyleFactory(pluralService));
  }

  @Bean
  @ConditionalOnMissingBean(name = "tableCodeGenContextProcessor")
  public CodeGenContextProcessor tableCodeGenContextProcessor() {
    return new TableCodeGenContextProcessor();
  }

  @Bean
  @ConditionalOnMissingBean(name = "domainCodeGenContextProcessor")
  public CodeGenContextProcessor domainCodeGenContextProcessor(CodeGenProperties properties, NameFactory nameFactory) {
    return new DomainCodeGenContextProcessor(properties.getTablePrefix(), nameFactory);
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
