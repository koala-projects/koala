package cn.koala.system.apis;

import cn.koala.system.Permission;
import cn.koala.system.entities.PermissionEntity;
import cn.koala.system.services.PermissionService;
import cn.koala.toolkit.tree.TreeNode;
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
@RequiredArgsConstructor
@RestController
public class PermissionApiImpl implements PermissionApi {
  protected final PermissionService service;

  @Override
  public DataResponse<List<TreeNode>> tree() {
    return DataResponse.ok(service.tree());
  }

  @Override
  public DataResponse<Permission> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Permission> create(PermissionEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, PermissionEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(PermissionEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
