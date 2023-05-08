package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.persist.support.DomainHelper;
import cn.koala.system.Role;
import cn.koala.system.repositories.RoleRepository;

import java.util.List;

/**
 * 角色服务实现类
 *
 * @author Houtaroy
 */
public class RoleServiceImpl extends AbstractMyBatisService<Role, Long> implements RoleService {

  /**
   * 字典服务实现类构造函数
   *
   * @param repository 字典仓库接口
   */
  public RoleServiceImpl(RoleRepository repository) {
    super(repository);
  }

  @Override
  public List<Long> getCheckedPermissionIds(Long id) {
    return ((RoleRepository) repository).findAllCheckedPermissionIdById(id);
  }

  @Override
  public void authorize(Long id, List<Long> checkedIds, List<Long> halfCheckedIds) {
    DomainHelper.assertEditable(repository.load(id));
    ((RoleRepository) repository).authorize(id, checkedIds, halfCheckedIds);
  }
}
