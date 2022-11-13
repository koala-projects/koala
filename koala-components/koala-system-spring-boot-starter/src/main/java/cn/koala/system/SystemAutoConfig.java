package cn.koala.system;

import cn.koala.system.mybatis.DepartmentRepository;
import cn.koala.system.mybatis.DepartmentServiceImpl;
import cn.koala.system.mybatis.DictionaryItemRepository;
import cn.koala.system.mybatis.DictionaryItemServiceImpl;
import cn.koala.system.mybatis.DictionaryRepository;
import cn.koala.system.mybatis.DictionaryServiceImpl;
import cn.koala.system.mybatis.PermissionRepository;
import cn.koala.system.mybatis.PermissionServiceImpl;
import cn.koala.system.mybatis.PersonalRepository;
import cn.koala.system.mybatis.PersonalServiceImpl;
import cn.koala.system.mybatis.RolePermissionRepository;
import cn.koala.system.mybatis.RoleRepository;
import cn.koala.system.mybatis.RoleServiceImpl;
import cn.koala.system.mybatis.UserDepartmentRepository;
import cn.koala.system.mybatis.UserDetailsRepository;
import cn.koala.system.mybatis.UserDetailsServiceImpl;
import cn.koala.system.mybatis.UserRepository;
import cn.koala.system.mybatis.UserRoleRepository;
import cn.koala.system.mybatis.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties(SystemProperties.class)
@MapperScan(basePackages = "cn.koala.system.mybatis")
public class SystemAutoConfig {

  /**
   * 字典服务的bean
   *
   * @param dictionaryRepository     字典存储库对象
   * @param dictionaryItemRepository 字典项存储库对象
   * @return 字典服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DictionaryService dictionaryService(DictionaryRepository dictionaryRepository,
                                             DictionaryItemRepository dictionaryItemRepository) {
    return new DictionaryServiceImpl(dictionaryRepository, dictionaryItemRepository);
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
    return new DictionaryItemServiceImpl(dictionaryItemRepository);
  }

  /**
   * 部门服务的bean
   *
   * @param departmentRepository 部门存储库
   * @param userRepository       用户存储库对象
   * @return 部门服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DepartmentService departmentService(DepartmentRepository departmentRepository, UserRepository userRepository) {
    return new DepartmentServiceImpl(departmentRepository, userRepository);
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
    return new PermissionServiceImpl(permissionRepository);
  }

  /**
   * 角色服务的bean
   *
   * @param roleRepository           角色存储库
   * @param userRepository           用户存储库对象
   * @param rolePermissionRepository 角色权限关系存储库
   * @return 角色服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public RoleService roleService(RoleRepository roleRepository, UserRepository userRepository,
                                 RolePermissionRepository rolePermissionRepository) {
    return new RoleServiceImpl(roleRepository, userRepository, rolePermissionRepository);
  }

  /**
   * 用户服务的bean
   *
   * @param passwordEncoder          密码加密器
   * @param systemProperties         系统参数
   * @param userRepository           用户存储库
   * @param userRoleRepository       用户角色关系存储库
   * @param userDepartmentRepository 用户部门存储库
   * @return 用户服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public UserService userService(PasswordEncoder passwordEncoder, SystemProperties systemProperties,
                                 UserRepository userRepository, UserDepartmentRepository userDepartmentRepository,
                                 UserRoleRepository userRoleRepository) {
    return new UserServiceImpl(
      passwordEncoder, systemProperties, userRepository, userDepartmentRepository, userRoleRepository
    );
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

  /**
   * 字典管理接口的bean
   *
   * @param dictionaryService 字典服务
   * @return 字典管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DictionaryApi dictionaryApi(DictionaryService dictionaryService) {
    return new DictionaryApiImpl(dictionaryService);
  }

  /**
   * 字典项管理接口的bean
   *
   * @param dictionaryItemService 字典项服务
   * @return 字典项管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DictionaryItemApi dictionaryItemApi(DictionaryItemService dictionaryItemService) {
    return new DictionaryItemApiImpl(dictionaryItemService);
  }

  /**
   * 部门管理接口的bean
   *
   * @param departmentService 部门服务
   * @return 部门管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public DepartmentApi departmentApi(DepartmentService departmentService) {
    return new DepartmentApiImpl(departmentService);
  }

  /**
   * 权限管理接口的bean
   *
   * @param permissionService 权限服务
   * @return 权限管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public PermissionApi permissionApi(PermissionService permissionService) {
    return new PermissionApiImpl(permissionService);
  }

  /**
   * 角色管理接口的bean
   *
   * @param roleService 角色服务
   * @return 角色管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public RoleApi roleApi(RoleService roleService) {
    return new RoleApiImpl(roleService);
  }

  /**
   * 用户管理接口的bean
   *
   * @param userService 用户服务
   * @return 用户管理接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public UserApi userApi(UserService userService) {
    return new UserApiImpl(userService);
  }


  /**
   * 用户信息接口的bean
   *
   * @param userDetailsService 用户详情服务
   * @return 用户信息接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public UserinfoApi userinfoApi(UserDetailsService userDetailsService) {
    return new UserinfoApiImpl(userDetailsService);
  }

  /**
   * 个人服务的bean
   *
   * @param userDetailsRepository 用户详情存储库
   * @param passwordEncoder       密码加密器
   * @param personalRepository    个人服务存储库
   * @return 个人服务对象
   */
  @Bean
  @ConditionalOnMissingBean
  public PersonalService personalService(UserDetailsRepository userDetailsRepository, PasswordEncoder passwordEncoder,
                                         PersonalRepository personalRepository) {
    return new PersonalServiceImpl(userDetailsRepository, passwordEncoder, personalRepository);
  }

  /**
   * 个人服务接口的bean
   *
   * @param personalService 个人服务对象
   * @return 个人服务接口对象
   */
  @Bean
  @ConditionalOnMissingBean
  public PersonalApi personalApi(PersonalService personalService) {
    return new PersonalApiImpl(personalService);
  }
}
