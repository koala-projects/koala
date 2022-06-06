package cn.houtaroy.koala.starter.system;

import cn.koala.system.UserApi;
import cn.koala.system.UserApiImpl;
import cn.koala.system.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class ApiConfig {
  /**
   * 用户接口的Bean
   *
   * @return 用户接口
   */
  @Bean
  public UserApi userApi(UserService userService) {
    return new UserApiImpl(userService);
  }
}
