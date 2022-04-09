package cn.houtaroy.koala.starter.system;

import cn.houtaroy.koala.api.UserApi;
import cn.houtaroy.koala.api.UserApiImpl;
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
  public UserApi userApi() {
    return new UserApiImpl();
  }
}
