package cn.koala.system.autoconfigure;

import cn.koala.system.api.DefaultDepartmentApi;
import cn.koala.system.api.DefaultUserApi;
import cn.koala.system.api.DepartmentApi;
import cn.koala.system.api.DictionaryApi;
import cn.koala.system.api.DictionaryApiImpl;
import cn.koala.system.api.DictionaryItemApi;
import cn.koala.system.api.DictionaryItemApiImpl;
import cn.koala.system.api.DutyApi;
import cn.koala.system.api.DutyApiImpl;
import cn.koala.system.api.PermissionApi;
import cn.koala.system.api.PermissionApiImpl;
import cn.koala.system.api.RoleApi;
import cn.koala.system.api.RoleApiImpl;
import cn.koala.system.api.UserApi;
import cn.koala.system.boot.AdminRegister;
import cn.koala.system.model.UserCreateListener;
import cn.koala.system.permission.PermissionRegister;
import cn.koala.system.permission.PermissionRegistrar;
import cn.koala.system.permission.SystemPermissionRegistrar;
import cn.koala.system.repository.DepartmentRepository;
import cn.koala.system.repository.DictionaryItemRepository;
import cn.koala.system.repository.DictionaryRepository;
import cn.koala.system.repository.DutyRepository;
import cn.koala.system.repository.PermissionRepository;
import cn.koala.system.repository.RoleRepository;
import cn.koala.system.repository.UserRepository;
import cn.koala.system.service.DefaultDepartmentService;
import cn.koala.system.service.DefaultUserService;
import cn.koala.system.service.DepartmentService;
import cn.koala.system.service.DictionaryItemService;
import cn.koala.system.service.DictionaryItemServiceImpl;
import cn.koala.system.service.DictionaryService;
import cn.koala.system.service.DictionaryServiceImpl;
import cn.koala.system.service.DutyService;
import cn.koala.system.service.DutyServiceImpl;
import cn.koala.system.service.PermissionService;
import cn.koala.system.service.PermissionServiceImpl;
import cn.koala.system.service.RoleService;
import cn.koala.system.service.RoleServiceImpl;
import cn.koala.system.service.UserService;
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
  public DutyService dutyService(DutyRepository dutyRepository) {
    return new DutyServiceImpl(dutyRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DutyApi dutyApi(DutyService dutyService) {
    return new DutyApiImpl(dutyService);
  }

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
    return new DefaultDepartmentService(departmentRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public DepartmentApi departmentApi(DepartmentService departmentService) {
    return new DefaultDepartmentApi(departmentService);
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
    return new RoleServiceImpl(roleRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public RoleApi roleApi(RoleService roleService) {
    return new RoleApiImpl(roleService);
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
  public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

    return new DefaultUserService(userRepository, passwordEncoder);
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
