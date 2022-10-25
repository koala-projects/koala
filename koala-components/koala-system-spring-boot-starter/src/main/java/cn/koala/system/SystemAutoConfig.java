package cn.koala.system;

import cn.koala.system.mybatis.DepartmentRepository;
import cn.koala.system.mybatis.DepartmentServiceImpl;
import cn.koala.system.mybatis.DictionaryItemRepository;
import cn.koala.system.mybatis.DictionaryRepository;
import cn.koala.system.mybatis.MyBatisDictionaryItemService;
import cn.koala.system.mybatis.MyBatisDictionaryService;
import cn.koala.system.mybatis.MyBatisPermissionService;
import cn.koala.system.mybatis.MyBatisRoleService;
import cn.koala.system.mybatis.PermissionRepository;
import cn.koala.system.mybatis.RolePermissionRepository;
import cn.koala.system.mybatis.RoleRepository;
import cn.koala.system.mybatis.UserDetailsRepository;
import cn.koala.system.mybatis.UserDetailsServiceImpl;
import cn.koala.system.mybatis.UserRepository;
import cn.koala.system.mybatis.UserRoleRepository;
import cn.koala.system.mybatis.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 系统管理自动配置
 *
 * @author Houtaroy
 */
@Configuration
@MapperScan(basePackages = "cn.koala.system.mybatis")
public class SystemAutoConfig {

  /**
   * 字典服务的bean
   *
   * @param dictionaryRepository 字典存储库
   * @return 字典服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DictionaryService dictionaryService(DictionaryRepository dictionaryRepository) {
    return new MyBatisDictionaryService(dictionaryRepository);
  }

  /**
   * 字典项服务的bean
   *
   * @param dictionaryItemRepository 字典项存储库
   * @return 字典项服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DictionaryItemService dictionaryItemService(DictionaryItemRepository dictionaryItemRepository) {
    return new MyBatisDictionaryItemService(dictionaryItemRepository);
  }

  /**
   * 部门服务的bean
   *
   * @param departmentRepository 部门存储库
   * @return 部门服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DepartmentService departmentService(DepartmentRepository departmentRepository) {
    return new DepartmentServiceImpl(departmentRepository);
  }

  /**
   * 权限服务的bean
   *
   * @param permissionRepository 权限存储库
   * @return 权限服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public PermissionService permissionService(PermissionRepository permissionRepository) {
    return new MyBatisPermissionService(permissionRepository);
  }

  /**
   * 角色服务的bean
   *
   * @param roleRepository           角色存储库
   * @param rolePermissionRepository 角色权限关系存储库
   * @return 角色服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public RoleService roleService(RoleRepository roleRepository, RolePermissionRepository rolePermissionRepository) {
    return new MyBatisRoleService(roleRepository, rolePermissionRepository);
  }

  /**
   * 用户服务的bean
   *
   * @param userRepository     用户存储库
   * @param passwordEncoder    密码加密器
   * @param userRoleRepository 用户角色关系存储库
   * @return 用户服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 UserRoleRepository userRoleRepository) {
    return new UserServiceImpl(userRepository, passwordEncoder, userRoleRepository);
  }

  /**
   * 用户详细信息服务的bean
   *
   * @param userDetailsRepository 用户详情存储库
   * @return 用户详细信息服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public UserDetailsService userDetailsService(UserDetailsRepository userDetailsRepository) {
    return new UserDetailsServiceImpl(userDetailsRepository);
  }
}
