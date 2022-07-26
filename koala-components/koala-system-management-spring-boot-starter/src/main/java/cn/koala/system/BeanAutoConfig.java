package cn.koala.system;

import cn.koala.system.mybatis.MyBatisPermissionService;
import cn.koala.system.mybatis.MyBatisRoleService;
import cn.koala.system.mybatis.MyBatisUserService;
import cn.koala.system.mybatis.PermissionRepository;
import cn.koala.system.mybatis.RoleRepository;
import cn.koala.system.mybatis.UserRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

/**
 * Bean自动配置
 *
 * @author Houtaroy
 */
@Configuration
public class BeanAutoConfig {

  /**
   * 密码编码器的bean
   *
   * @return 密码编码器
   */
  @Bean
  @ConditionalOnMissingBean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /**
   * 用户服务的bean
   *
   * @param userRepository 用户存储库对象
   * @return 用户服务
   */
  @Bean
  @ConditionalOnMissingBean
  public UserService userService(
    UserRepository userRepository, PasswordEncoder passwordEncoder, SystemManagementProperties properties
  ) {
    return new MyBatisUserService(userRepository, passwordEncoder, properties);
  }

  /**
   * 角色服务的bean
   *
   * @param roleRepository 角色存储库对象
   * @return 角色服务
   */
  @Bean
  @ConditionalOnMissingBean
  public RoleService roleService(RoleRepository roleRepository) {
    return new MyBatisRoleService(roleRepository);
  }

  /**
   * 权限服务的bean
   *
   * @param permissionRepository 权限存储库对象
   * @return 权限服务
   */
  @Bean
  @ConditionalOnMissingBean
  public PermissionService permissionService(PermissionRepository permissionRepository) {
    return new MyBatisPermissionService(permissionRepository);
  }

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

  /**
   * Jwt定制器的bean
   *
   * @return Jwt定制器
   */
  @Bean
  @ConditionalOnMissingBean
  public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
    return new JwtCustomizer();
  }
}
