package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.User;
import cn.koala.system.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class UserServiceImpl extends AbstractMyBatisService<User, Long> implements UserService {

  protected final UserRepository repository;

  @Override
  public List<Long> getRoleIds(Long id) {
    return repository.findAllRoleIdById(id);
  }

  @Override
  public void setRoleIds(Long id, List<Long> roleIds) {
    repository.updateRoleIdById(id, roleIds);
  }

  @Override
  public List<Long> getDepartmentIds(Long id) {
    return repository.findAllDepartmentIdById(id);
  }

  @Override
  public void setDepartmentIds(Long id, List<Long> departmentIds) {
    repository.updateDepartmentIdById(id, departmentIds);
  }
}
