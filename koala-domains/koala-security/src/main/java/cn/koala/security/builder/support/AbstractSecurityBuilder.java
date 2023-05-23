package cn.koala.security.builder.support;

import cn.koala.security.builder.SecurityBuilder;
import cn.koala.security.builder.processor.HttpSecurityProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

/**
 * 安全构建器抽象类
 * <p>
 * 内置了{@link HttpSecurityProcessor}数组, 所有配置操作通过其完成
 *
 * @author Houtaroy
 */
public abstract class AbstractSecurityBuilder implements SecurityBuilder {

  protected final List<? extends HttpSecurityProcessor> processors;

  public AbstractSecurityBuilder(List<? extends HttpSecurityProcessor> processors) {
    this.processors = processors;
  }

  @Override
  public SecurityFilterChain build(HttpSecurity http) throws Exception {
    for (HttpSecurityProcessor processor : processors) {
      processor.preBuild(http);
    }
    SecurityFilterChain result = http.build();
    for (HttpSecurityProcessor processor : processors) {
      processor.postBuild(http);
    }
    return result;
  }
}
