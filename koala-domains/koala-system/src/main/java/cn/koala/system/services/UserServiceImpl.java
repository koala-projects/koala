package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.system.User;
import cn.koala.system.entities.UserEntity;
import cn.koala.system.repositories.UserRepository;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
public class UserServiceImpl extends AbstractMyBatisService<User, Long> implements UserService {

  /**
   * 用户服务实现类构造函数
   *
   * @param repository 用户仓库接口
   */
  public UserServiceImpl(UserRepository repository) {
    super(repository);
  }

  @Override
  public List<Long> getRoleIds(Long id) {
    return ((UserRepository) repository).findAllRoleIdById(id);
  }

  @Override
  public void setRoleIds(Long id, List<Long> roleIds) {
    preCheckBeforeUpdateAndDelete(UserEntity.builder().id(id).build());
    ((UserRepository) repository).updateRoleIdById(id, roleIds);
  }

  @Override
  public List<Long> getDepartmentIds(Long id) {
    return ((UserRepository) repository).findAllDepartmentIdById(id);
  }

  @Override
  public void setDepartmentIds(Long id, List<Long> departmentIds) {
    preCheckBeforeUpdateAndDelete(UserEntity.builder().id(id).build());
    ((UserRepository) repository).updateDepartmentIdById(id, departmentIds);
  }
}
