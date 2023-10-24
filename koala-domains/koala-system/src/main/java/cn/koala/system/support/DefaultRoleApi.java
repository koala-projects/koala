package cn.koala.system.support;

import cn.koala.log.annotations.Log;
import cn.koala.system.Role;
import cn.koala.system.RoleApi;
import cn.koala.system.RoleService;
import cn.koala.system.User;
import cn.koala.system.apis.request.RoleAuthorizeRequest;
import cn.koala.system.entities.RoleEntity;
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
public class DefaultRoleApi implements RoleApi {
  protected final RoleService roleService;

  @Override
  @Log(module = "角色管理", content = "查询角色列表")
  public DataResponse<Page<Role>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(roleService.page(parameters, pageable));
  }

  @Override
  @Log(module = "角色管理", content = "查看角色[id=${#id}]")
  public DataResponse<Role> load(Long id) {
    return DataResponse.ok(roleService.load(id));
  }

  @Override
  @Log(module = "角色管理", content = "创建角色[code=${#entity.code}]")
  public DataResponse<Role> create(RoleEntity entity) {
    roleService.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  @Log(module = "角色管理", content = "更新角色[id=${#id}]")
  public Response update(Long id, RoleEntity entity) {
    entity.setIdIfAbsent(id);
    roleService.update(entity);
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "角色管理", content = "删除角色[id=${#id}]")
  public Response delete(Long id) {
    roleService.delete(RoleEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "角色管理", content = "获取角色[id=${#id}]权限代码列表")
  public DataResponse<List<Long>> getCheckedPermissionIds(Long id) {
    return DataResponse.ok(roleService.getCheckedPermissionIds(id));
  }

  @Override
  @Log(module = "角色管理", content = "设置角色[id=${#id}]权限")
  public Response setRolePermissions(Long id, RoleAuthorizeRequest request) {
    roleService.authorize(id, request.getCheckedIds(), request.getHalfCheckedIds());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<User>> listUser(Long id) {
    return DataResponse.ok(roleService.listUser(id));
  }
}
