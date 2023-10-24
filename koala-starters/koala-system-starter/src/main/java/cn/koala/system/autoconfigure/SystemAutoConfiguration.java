package cn.koala.system.autoconfigure;

import cn.koala.system.PermissionRegistrar;
import cn.koala.system.RoleService;
import cn.koala.system.UserApi;
import cn.koala.system.UserCreateListener;
import cn.koala.system.apis.DepartmentApi;
import cn.koala.system.apis.DepartmentApiImpl;
import cn.koala.system.apis.DictionaryApi;
import cn.koala.system.apis.DictionaryApiImpl;
import cn.koala.system.apis.DictionaryItemApi;
import cn.koala.system.apis.DictionaryItemApiImpl;
import cn.koala.system.apis.PermissionApi;
import cn.koala.system.apis.PermissionApiImpl;
import cn.koala.system.repository.DepartmentRepository;
import cn.koala.system.repository.DictionaryItemRepository;
import cn.koala.system.repository.DictionaryRepository;
import cn.koala.system.repository.PermissionRepository;
import cn.koala.system.repository.RoleRepository;
import cn.koala.system.repository.UserRepository;
import cn.koala.system.services.DepartmentService;
import cn.koala.system.services.DepartmentServiceImpl;
import cn.koala.system.services.DictionaryItemService;
import cn.koala.system.services.DictionaryItemServiceImpl;
import cn.koala.system.services.DictionaryService;
import cn.koala.system.services.DictionaryServiceImpl;
import cn.koala.system.services.PermissionService;
import cn.koala.system.services.PermissionServiceImpl;
import cn.koala.system.services.UserService;
import cn.koala.system.services.UserServiceImpl;
import cn.koala.system.support.AdminRegister;
import cn.koala.system.support.DefaultRoleApi;
import cn.koala.system.support.DefaultRoleService;
import cn.koala.system.support.DefaultUserApi;
import cn.koala.system.support.PermissionRegister;
import cn.koala.system.support.SystemPermissionRegistrar;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * 系统管理自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
@MapperScan(basePackages = "cn.koala.system.repository")
public class SystemAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean
  public DictionaryService dictionaryService(DictionaryRepository dictionaryRepository) {
    return new DictionaryServiceImpl(dictionaryRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DictionaryApi dictionaryApi(DictionaryService dictionaryService) {
    return new DictionaryApiImpl(dictionaryService);
  }

  @Bean
  @ConditionalOnMissingBean
  public DictionaryItemService dictionaryItemService(DictionaryItemRepository dictionaryItemRepository) {
    return new DictionaryItemServiceImpl(dictionaryItemRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DictionaryItemApi dictionaryItemApi(DictionaryItemService dictionaryItemService) {
    return new DictionaryItemApiImpl(dictionaryItemService);
  }

  @Bean
  @ConditionalOnMissingBean
  public DepartmentService departmentService(DepartmentRepository departmentRepository) {
    return new DepartmentServiceImpl(departmentRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DepartmentApi departmentApi(DepartmentService departmentService) {
    return new DepartmentApiImpl(departmentService);
  }

  @Bean
  @ConditionalOnMissingBean
  public PermissionService permissionService(PermissionRepository permissionRepository) {
    return new PermissionServiceImpl(permissionRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public PermissionApi permissionApi(PermissionService permissionService) {
    return new PermissionApiImpl(permissionService);
  }

  @Bean
  @ConditionalOnMissingBean
  public RoleService roleService(RoleRepository roleRepository) {
    return new DefaultRoleService(roleRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DefaultRoleApi roleApi(RoleService roleService) {
    return new DefaultRoleApi(roleService);
  }

  @Bean
  @ConditionalOnMissingBean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @ConditionalOnMissingBean(name = "userCreateListener")
  public UserCreateListener userCreateListener(PasswordEncoder passwordEncoder) {
    return new UserCreateListener(passwordEncoder);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserService userService(UserRepository userRepository) {
    return new UserServiceImpl(userRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public UserApi userApi(UserService userService) {
    return new DefaultUserApi(userService);
  }

  @Bean
  @ConditionalOnMissingBean(name = "systemPermissionRegistrar")
  public PermissionRegistrar systemPermissionRegistrar() {
    return new SystemPermissionRegistrar();
  }

  @Bean
  public PermissionRegister permissionRegister(
    List<PermissionRegistrar> registrars, SystemProperties properties, PermissionRepository repository) {

    return new PermissionRegister(registrars, repository, properties.getPermissionRegistrars());
  }

  @Bean
  @ConditionalOnProperty(prefix = "koala.system", name = "admin-register", havingValue = "true")
  public AdminRegister adminRegister(PermissionRepository permissionRepository, RoleRepository roleRepository,
                                     UserRepository userRepository, PasswordEncoder passwordEncoder) {

    return new AdminRegister(permissionRepository, roleRepository, userRepository, passwordEncoder);
  }
}
