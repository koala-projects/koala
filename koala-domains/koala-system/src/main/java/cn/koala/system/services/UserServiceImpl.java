package cn.koala.system.services;

import cn.koala.system.User;
import cn.koala.system.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
public class UserServiceImpl extends BaseSystemService<User> implements UserService {
  protected final PasswordEncoder passwordEncoder;

  /**
   * 用户服务实现类构造函数
   *
   * @param repository      用户仓库接口
   * @param passwordEncoder 密码加密器
   */
  public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
    super(repository);
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public <S extends User> void add(S entity) {
    if (StringUtils.hasLength(entity.getPassword())) {
      entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    }
    super.add(entity);
  }

  @Override
  public List<Long> getRoleIds(Long id) {
    return ((UserRepository) repository).findAllRoleIdById(id);
  }

  @Override
  public void setRoleIds(Long id, List<Long> roleIds) {
    Assert.isTrue(nonSystem(load(id)), "系统数据不允许修改");
    ((UserRepository) repository).updateRoleIdById(id, roleIds);
  }

  @Override
  public List<Long> getDepartmentIds(Long id) {
    return ((UserRepository) repository).findAllDepartmentIdById(id);
  }

  @Override
  public void setDepartmentIds(Long id, List<Long> departmentIds) {
    Assert.isTrue(nonSystem(load(id)), "系统数据不允许修改");
    ((UserRepository) repository).updateDepartmentIdById(id, departmentIds);
  }
}
