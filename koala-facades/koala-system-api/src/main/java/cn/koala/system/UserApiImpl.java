package cn.koala.system;

import cn.koala.web.DataRequest;
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
public class UserApiImpl implements UserApi {
  protected final UserService service;

  @Override
  public DataResponse<Page<User>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }

  @Override
  public DataResponse<User> loadById(String id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }

  @Override
  public DataResponse<User> create(UserEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(String id, UserEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    service.delete(UserEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<String>> listDepartmentIds(String id) {
    return DataResponse.ok(service.listDepartmentIds(id));
  }

  @Override
  public Response setDepartmentIds(String id, DataRequest<List<String>> request) {
    service.setDepartmentIds(id, request.getData());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<String>> listRoleIds(String id) {
    return DataResponse.ok(service.listRoleIds(id));
  }

  @Override
  public Response setRoleIds(String id, DataRequest<List<String>> request) {
    service.setRoleIds(id, request.getData());
    return Response.SUCCESS;
  }
}
