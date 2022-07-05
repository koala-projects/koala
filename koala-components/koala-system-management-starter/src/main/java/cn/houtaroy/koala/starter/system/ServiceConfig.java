package cn.houtaroy.koala.starter.system;

import cn.koala.system.UserService;
import cn.koala.system.mybatis.MyBatisUserService;
import cn.koala.system.mybatis.UserRepository;
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
    return new MyBatisUserService(userRepository);
  }
}
