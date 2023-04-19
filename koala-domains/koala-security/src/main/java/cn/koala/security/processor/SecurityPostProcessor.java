package cn.koala.security.processor;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 安全后置处理器
 *
 * @author Houtaroy
 */
public interface SecurityPostProcessor {
  void postProcessBeforeInitialization(HttpSecurity http) throws Exception;

  void postProcessAfterInitialization(HttpSecurity http) throws Exception;
}
