package cn.koala.system.boot;

import cn.koala.persist.domain.YesNo;
import cn.koala.system.model.Permission;
import cn.koala.system.model.Role;
import cn.koala.system.model.RoleEntity;
import cn.koala.system.model.User;
import cn.koala.system.model.UserEntity;
import cn.koala.system.repository.PermissionRepository;
import cn.koala.system.repository.RoleRepository;
import cn.koala.system.repository.UserRepository;
import cn.koala.toolkit.DateHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 管理员注册器
 *
 * @author Houtaroy
 */
@Order(3300)
@Slf4j
@RequiredArgsConstructor
public class AdminRegister implements ApplicationRunner {

  private final static Long ADMIN_ID = 1L;

  private final PermissionRepository permissionRepository;

  private final RoleRepository roleRepository;

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;


  @Override
  public void run(ApplicationArguments args) {
    this.registerAdminRole();
    this.registerAdminUser();
  }

  private void registerAdminRole() {
    Role adminRole = this.obtainAdminRole();
    Optional<Role> persist = this.roleRepository.load(adminRole.getId());
    if (persist.isPresent()) {
      LOGGER.warn("[koala-system]: 系统管理员角色已存在，跳过自动注册");
      return;
    }
    this.roleRepository.create(adminRole);
    List<Permission> permissions = this.permissionRepository.list(Map.of());
    this.roleRepository.authorize(adminRole.getId(), permissions.stream().map(Permission::getId).toList(), List.of());
  }

  private Role obtainAdminRole() {
    return RoleEntity.builder()
      .id(ADMIN_ID)
      .code("admin")
      .name("系统管理员")
      .sortIndex(ADMIN_ID)
      .isSystemic(YesNo.YES)
      .isEnabled(YesNo.YES)
      .isDeleted(YesNo.NO)
      .createdBy(ADMIN_ID)
      .createdTime(DateHelper.now())
      .build();
  }

  private void registerAdminUser() {
    User adminUser = this.obtainAdminUser();
    Optional<User> persist = this.userRepository.load(adminUser.getId());
    if (persist.isPresent()) {
      LOGGER.warn("[koala-system]: 系统管理员用户已存在，跳过自动注册");
      return;
    }
    this.userRepository.create(adminUser);
    this.userRepository.updateDepartmentIdById(adminUser.getId(), List.of(ADMIN_ID));
    this.userRepository.updateRoleIdById(adminUser.getId(), List.of(ADMIN_ID));
  }

  private User obtainAdminUser() {
    return UserEntity.builder()
      .id(ADMIN_ID)
      .username("admin")
      .password(this.passwordEncoder.encode("123456"))
      .nickname("系统管理员")
      .sortIndex(ADMIN_ID)
      .isSystemic(YesNo.YES)
      .isEnabled(YesNo.YES)
      .isDeleted(YesNo.NO)
      .createdBy(ADMIN_ID)
      .createdTime(DateHelper.now())
      .build();
  }
}
