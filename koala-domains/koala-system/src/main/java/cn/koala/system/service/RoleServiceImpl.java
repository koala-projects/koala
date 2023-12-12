package cn.koala.system.service;

import cn.koala.data.util.DomainUtils;
import cn.koala.exception.BusinessException;
import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.domain.Role;
import cn.koala.system.domain.User;
import cn.koala.system.repository.RoleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;

import java.util.List;

/**
 * 角色服务实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@Getter
public class RoleServiceImpl extends AbstractSmartService<Long, Role, Long> implements RoleService {

  private final RoleRepository repository;
  private final AuditorAware<Long> auditorAware;

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
