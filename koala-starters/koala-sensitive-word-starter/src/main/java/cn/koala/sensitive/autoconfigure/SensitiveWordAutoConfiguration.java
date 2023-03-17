package cn.koala.sensitive.autoconfigure;

import cn.koala.sensitive.InMemorySensitiveWordService;
import cn.koala.sensitive.SensitiveWordFilter;
import cn.koala.sensitive.SensitiveWordService;
import cn.koala.sensitive.SimpleSensitiveWordFilter;
import cn.koala.sensitive.apis.SensitiveWordApi;
import cn.koala.sensitive.apis.SensitiveWordApiImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词自动配置类
 *
 * @author Houtaroy
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SensitiveWordProperties.class)
public class SensitiveWordAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public SensitiveWordService sensitiveWordService(SensitiveWordProperties properties) {
    return new InMemorySensitiveWordService(getWords(properties));
  }

  @Bean
  @ConditionalOnMissingBean
  public SensitiveWordFilter sensitiveWordFilter(SensitiveWordService sensitiveWordService) {
    return new SimpleSensitiveWordFilter(sensitiveWordService.list());
  }

  @Bean
  @ConditionalOnMissingBean
  public SensitiveWordApi sensitiveWordApi(SensitiveWordFilter sensitiveWordFilter) {
    return new SensitiveWordApiImpl(sensitiveWordFilter);
  }

  private List<String> getWords(SensitiveWordProperties properties) {
    List<String> result = new ArrayList<>();
    if (StringUtils.hasLength(properties.getWordFile())) {
      try {
        result.addAll(FileUtils.readLines(new File(properties.getWordFile()), Charset.defaultCharset()));
      } catch (IOException e) {
        LOGGER.error("读取词库文件[path=%s]失败".formatted(properties.getWordFile()), e);
      }
    }
    return result;
  }
}
