package cn.koala.system.service;

import cn.koala.exception.BusinessException;
import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.persist.util.DomainUtils;
import cn.koala.system.model.Role;
import cn.koala.system.model.User;
import cn.koala.system.repository.RoleRepository;
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
    Role persist = repository.load(id).orElseThrow(() -> new BusinessException("角色不存在"));
    if (DomainUtils.isSystemic(persist)) {
      throw new BusinessException("角色不允许修改");
    }
    repository.authorize(id, checkedIds, halfCheckedIds);
  }

  @Override
  public List<User> listUser(Long id) {
    return repository.listUserById(id);
  }
}
