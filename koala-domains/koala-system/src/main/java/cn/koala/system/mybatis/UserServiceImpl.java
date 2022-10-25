package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractUUIDCrudService;
import cn.koala.system.User;
import cn.koala.system.UserService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class UserServiceImpl extends AbstractUUIDCrudService<User> implements UserService {
  protected final UserRepository repository;
  protected final PasswordEncoder passwordEncoder;
  protected final UserRoleRepository userRoleRepository;

  @Override
  public void add(User entity) {
    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    super.add(entity);
  }

  @Override
  public void delete(User entity) {
    super.delete(entity);
    userRoleRepository.deleteByUserId(entity.getId());
  }

  @Override
  public List<String> roleIds(String id) {
    return userRoleRepository.findAllRoleIdByUserId(id);
  }

  @Override
  public void setRoles(String id, List<String> roleIds) {
    userRoleRepository.add(id, roleIds);
  }
}
