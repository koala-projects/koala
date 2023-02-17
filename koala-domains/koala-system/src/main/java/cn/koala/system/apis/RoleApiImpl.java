package cn.koala.system.apis;

import cn.koala.system.Role;
import cn.koala.system.entities.RoleEntity;
import cn.koala.system.services.RoleService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 角色接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class RoleApiImpl implements RoleApi {
  protected final RoleService roleService;

  @Override
  public DataResponse<Page<Role>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(roleService.page(parameters, pageable));
  }

  @Override
  public DataResponse<Role> load(Long id) {
    return DataResponse.ok(roleService.load(id));
  }

  @Override
  public DataResponse<Role> create(RoleEntity entity) {
    roleService.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, RoleEntity entity) {
    entity.setIdIfAbsent(id);
    roleService.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    roleService.delete(RoleEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<Long>> getCheckedPermissionIds(Long id) {
    return DataResponse.ok(roleService.getCheckedPermissionIds(id));
  }

  @Override
  public Response setRolePermissions(Long id, RoleAuthorizeRequest request) {
    roleService.authorize(id, request.getCheckedIds(), request.getHalfCheckedIds());
    return Response.SUCCESS;
  }
}
