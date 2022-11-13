package cn.koala.system.mybatis;

import cn.koala.system.SystemProperties;
import cn.koala.system.User;
import cn.koala.system.UserEntity;
import cn.koala.system.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractSystemService<User> implements UserService {
  protected final PasswordEncoder passwordEncoder;
  protected final SystemProperties properties;
  protected final UserRepository repository;
  protected final UserDepartmentRepository userDepartmentRepository;
  protected final UserRoleRepository userRoleRepository;

  @Override
  public void add(User entity) {
    entity.setPassword(passwordEncoder.encode(
      Optional.ofNullable(entity.getPassword()).orElse(properties.getDefaultPassword())
    ));
    super.add(entity);
  }

  @Override
  public void delete(User entity) {
    super.delete(entity);
    userDepartmentRepository.deleteByUserId(entity.getId());
    userRoleRepository.deleteByUserId(entity.getId());
  }

  @Override
  public List<String> listDepartmentIds(String id) {
    return userDepartmentRepository.findAllDepartmentIdByUserId(id);
  }

  @Override
  public void setDepartmentIds(String id, List<String> departmentIds) {
    Assert.isTrue(isNoSystem(UserEntity.builder().id(id).build()), "权限不足, 请联系管理员");
    userDepartmentRepository.deleteByUserId(id);
    if (departmentIds.isEmpty()) {
      return;
    }
    userDepartmentRepository.add(id, departmentIds);
  }

  @Override
  public List<String> listRoleIds(String id) {
    return userRoleRepository.findAllRoleIdByUserId(id);
  }

  @Override
  public void setRoleIds(String id, List<String> roleIds) {
    Assert.isTrue(isNoSystem(UserEntity.builder().id(id).build()), "权限不足, 请联系管理员");
    userRoleRepository.deleteByUserId(id);
    if (roleIds.isEmpty()) {
      return;
    }
    userRoleRepository.add(id, roleIds);
  }
}
