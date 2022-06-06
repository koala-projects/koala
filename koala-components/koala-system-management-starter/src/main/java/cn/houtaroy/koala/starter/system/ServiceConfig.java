package cn.houtaroy.koala.starter.system;

import cn.koala.system.repositories.UserRepository;
import cn.koala.system.services.UserService;
import cn.koala.system.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class ServiceConfig {
  /**
   * 用户服务的bean
   *
   * @param userRepository 用户存储库对象
   * @return 用户服务
   */
  @Bean
  public UserService userService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }
}
