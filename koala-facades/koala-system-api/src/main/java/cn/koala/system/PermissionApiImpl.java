package cn.koala.system;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class PermissionApiImpl implements PermissionApi {
  protected final PermissionService service;

  @Override
  public DataResponse<Page<Permission>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }

  @Override
  public DataResponse<Permission> loadById(String id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }

  @Override
  public DataResponse<Permission> create(PermissionEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(String id, PermissionEntity entity) {
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    service.delete(PermissionEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
