package cn.koala.system;

import cn.koala.system.mybatis.PermissionEntity;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
@RequiredArgsConstructor
@RestController
public class PermissionApiImpl implements PermissionApi {
  protected final PermissionService permissionService;

  @Override
  public DataResponse<Page<Permission>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(permissionService.list(parameters, pageable));
  }

  @Override
  public DataResponse<Permission> loadById(String id) {
    return DataResponse.ok(permissionService.load(id).orElse(null));
  }

  @Override
  public DataResponse<Permission> create(PermissionEntity permission) {
    permissionService.add(permission);
    return DataResponse.ok(permission);
  }

  @Override
  public Response update(String id, PermissionEntity permission) {
    permissionService.update(permission);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    permissionService.delete(PermissionEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
