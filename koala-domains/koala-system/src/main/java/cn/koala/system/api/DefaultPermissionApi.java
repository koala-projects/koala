package cn.koala.system.api;

import cn.koala.log.annotation.Log;
import cn.koala.system.domain.Permission;
import cn.koala.system.domain.PermissionEntity;
import cn.koala.system.service.PermissionService;
import cn.koala.util.TreeNode;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限接口实现类
 *
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class DefaultPermissionApi implements PermissionApi {

  private final PermissionService service;

  @Override
  @Log(module = "权限管理", content = "查询权限树")
  public DataResponse<List<TreeNode>> tree() {
    return DataResponse.ok(service.tree());
  }

  @Override
  @Log(module = "权限管理", content = "查看权限[id=${#id}]")
  public DataResponse<Permission> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  @Log(module = "权限管理", content = "创建权限[code=${#entity.code}]")
  public DataResponse<Permission> create(PermissionEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  @Log(module = "权限管理", content = "更新权限[id=${#id}]")
  public Response update(Long id, PermissionEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "权限管理", content = "删除权限[id=${#id}]")
  public Response delete(Long id) {
    service.delete(PermissionEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
