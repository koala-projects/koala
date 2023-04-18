package cn.koala.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * OAuth2授权服务
 *
 * @author Houtaroy
 */
public interface AuthorizationServerPostProcessor {
  void postProcessBeforeInitialization(HttpSecurity http);

  void postProcessAfterInitialization(HttpSecurity http);
}
