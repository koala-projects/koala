package cn.koala.system;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 * @date 2022/4/10
 */
@RequiredArgsConstructor
@RestController
public class RoleApiImpl implements RoleApi {
  protected final RoleService roleService;

  @Override
  public DataResponse<Page<Role>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(roleService.list(parameters, pageable));
  }

  @Override
  public DataResponse<Role> loadById(String id) {
    return DataResponse.ok(roleService.load(id).orElse(null));
  }

  @Override
  public DataResponse<Role> create(RoleEntity entity) {
    roleService.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(String id, RoleEntity entity) {
    entity.setId(id);
    roleService.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    roleService.delete(RoleEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<String>> permissionIds(String id) {
    return DataResponse.ok(roleService.permissionIds(id));
  }

  @Override
  public Response authorize(String id, List<String> permissionIds) {
    roleService.authorize(id, permissionIds);
    return Response.SUCCESS;
  }
}
