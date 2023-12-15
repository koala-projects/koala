package cn.koala.system.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.system.domain.User;
import cn.koala.system.repository.UserRepository;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * 用户服务实现类
 *
 * @author Houtaroy
 */
@Getter
@RequiredArgsConstructor
public class DefaultUserService extends AbstractSmartService<Long, User, Long> implements UserService {

  private final UserRepository repository;

  private final AuditorAware<Long> auditorAware;

  private final PasswordEncoder passwordEncoder;

  @Override
  public <S extends User> void create(@NonNull S entity) {
    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    super.create(entity);
    entity.setPassword(null);
  }

  @Override
  public <S extends User> void update(@NonNull S entity) {
    entity.setUsername(null);
    entity.setPassword(null);
    super.update(entity);
  }

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

  @Override
  public List<Long> listDutyIds(Long id) {
    return repository.findAllDutyIdById(id);
  }

  @Override
  public void updateDuties(Long id, List<Long> dutyIds) {
    repository.updateDutyIdById(id, dutyIds);
  }
}
