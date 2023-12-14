package cn.koala.code.autoconfigure;

import cn.koala.codegen.name.AppendPluralConverter;
import cn.koala.codegen.name.NameFactory;
import cn.koala.codegen.name.NameStyleFactory;
import cn.koala.codegen.name.PluralConverter;
import cn.koala.codegen.name.PluralService;
import cn.koala.codegen.name.ReplacePluralConverter;
import cn.koala.codegen.name.SimplePluralService;
import cn.koala.codegen.name.StaticPluralConverter;
import com.google.common.collect.Maps;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Houtaroy
 */
@Configuration
public class NameFactoryAutoConfiguration {

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
}
