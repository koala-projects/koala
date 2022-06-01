package cn.koala.security;
/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import cn.koala.system.repositories.UserRepository;
import cn.koala.system.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Joe Grandja
 * @since 0.1.0
 */
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {
  /**
   * 授权服务器默认安全过滤器链配置
   *
   * @param http HttpSecurity对象
   * @return 安全过滤器链实例
   * @throws Exception 异常
   */
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
      .formLogin(withDefaults()).build();
  }

  /**
   * 用户信息服务配置
   *
   * @return 用户信息服务实例
   */
  @Bean
  @ConditionalOnMissingBean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }
}
