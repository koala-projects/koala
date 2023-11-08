package cn.koala.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 安全过滤链附加处理器
 *
 * @author Houtaroy
 */
public interface SecurityFilterChainPostProcessor {

  void postProcessBeforeBuild(HttpSecurity http) throws Exception;

  void postProcessAfterBuild(HttpSecurity http) throws Exception;
}
