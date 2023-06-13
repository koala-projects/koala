package cn.koala.security.authentication.builder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 安全构建器
 *
 * @author Houtaroy
 */
public interface SecurityBuilder {
  SecurityFilterChain build(HttpSecurity http) throws Exception;
}
