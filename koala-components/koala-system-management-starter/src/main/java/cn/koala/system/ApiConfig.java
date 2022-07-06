package cn.koala.system;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
  @ConditionalOnMissingBean
  public UserApi userApi(UserService userService) {
    return new UserApiImpl(userService);
  }

  /**
   * 用户接口的Bean
   *
   * @return 用户接口
   */
  @Bean
  @ConditionalOnMissingBean
  public RoleApi roleApi(RoleService roleService) {
    return new RoleApiImpl(roleService);
  }

  /**
   * 用户接口的Bean
   *
   * @return 用户接口
   */
  @Bean
  @ConditionalOnMissingBean
  public PermissionApi permissionApi(PermissionService permissionService) {
    return new PermissionApiImpl(permissionService);
  }
}
