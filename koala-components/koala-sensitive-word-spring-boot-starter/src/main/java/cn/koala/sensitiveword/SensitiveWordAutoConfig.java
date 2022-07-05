package cn.koala.sensitiveword;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(SensitiveWordProperties.class)
@RequiredArgsConstructor
@Slf4j
public class SensitiveWordAutoConfig {
  private final ResourceLoader resourceLoader;
  private final SensitiveWordProperties properties;

  /**
   * 敏感词过滤链的Bean
   *
   * @param filters 敏感词过滤器列表
   * @return 敏感词过滤链
   */
  @Bean
  @ConditionalOnMissingBean
  public RefreshableSensitiveWordFilterChain sensitiveWordFilterChain(List<SensitiveWordFilter> filters) {
    List<SensitiveWordFilter> result = attemptAddResourceSensitiveWordFilter();
    result.addAll(filters);
    return new DefaultRefreshableSensitiveWordFilterChain(result);
  }

  /**
   * 尝试新增资源敏感词过滤器
   *
   * @return 资源敏感词过滤器列表
   */
  protected List<SensitiveWordFilter> attemptAddResourceSensitiveWordFilter() {
    List<SensitiveWordFilter> result = new ArrayList<>();
    if (!properties.getResourceLocations().isEmpty()) {
      properties.getResourceLocations().forEach(location -> attemptAddResourceSensitiveWordFilter(result, location));
    }
    return result;
  }

  /**
   * 尝试新增资源敏感词过滤器
   *
   * @param filters  资源敏感词过滤器列表
   * @param location 资源定位
   */
  protected void attemptAddResourceSensitiveWordFilter(List<SensitiveWordFilter> filters, String location) {
    Resource resource = resourceLoader.getResource(location);
    if (resource.exists()) {
      try {
        filters.add(new FileSensitiveWordFilter(resource.getFile().getPath()));
      } catch (Exception e) {
        LOGGER.error("添加资源敏感词过滤器失败", e);
      }
    }
  }
}
