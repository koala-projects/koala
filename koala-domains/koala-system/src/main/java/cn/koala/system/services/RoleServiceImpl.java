package cn.koala.system.services;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.persist.support.DomainHelper;
import cn.koala.system.Role;
import cn.koala.system.repositories.RoleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 角色服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class RoleServiceImpl extends AbstractMyBatisService<Role, Long> implements RoleService {

  protected final RoleRepository repository;

  @Override
  public List<Long> getCheckedPermissionIds(Long id) {
    return getRepository().findAllCheckedPermissionIdById(id);
  }

  @Override
  public void authorize(Long id, List<Long> checkedIds, List<Long> halfCheckedIds) {
    DomainHelper.assertEditable(repository.load(id));
    getRepository().authorize(id, checkedIds, halfCheckedIds);
  }
}
