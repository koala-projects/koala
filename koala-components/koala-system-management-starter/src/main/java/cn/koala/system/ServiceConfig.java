package cn.koala.system;

import cn.koala.security.AuthorizationServerConfig;
import cn.koala.system.mybatis.MyBatisPermissionService;
import cn.koala.system.mybatis.MyBatisRoleService;
import cn.koala.system.mybatis.MyBatisUserService;
import cn.koala.system.mybatis.PermissionRepository;
import cn.koala.system.mybatis.RoleRepository;
import cn.koala.system.mybatis.UserRepository;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@AutoConfigureBefore(AuthorizationServerConfig.class)
@Configuration
public class ServiceConfig {

  /**
   * 用户服务的bean
   *
   * @param userRepository 用户存储库对象
   * @return 用户服务
   */
  @Bean
  @ConditionalOnMissingBean
  public UserService userService(UserRepository userRepository) {
    return new MyBatisUserService(userRepository);
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
}
