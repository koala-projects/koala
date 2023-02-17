package cn.koala.system.apis;

import cn.koala.system.User;
import cn.koala.system.entities.UserEntity;
import cn.koala.system.services.UserService;
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
 * 用户接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class UserApiImpl implements UserApi {
  protected final UserService service;

  @Override
  public DataResponse<Page<User>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<User> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<User> create(UserEntity user) {
    service.save(user);
    return DataResponse.ok(user);
  }

  @Override
  public Response update(Long id, UserEntity user) {
    user.setIdIfAbsent(id);
    service.save(user);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(UserEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<Long>> getRoleIds(Long id) {
    return DataResponse.ok(service.getRoleIds(id));
  }

  @Override
  public Response setRoleIds(Long id, DataRequest<List<Long>> request) {
    service.setRoleIds(id, request.getData());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<Long>> getDepartmentIds(Long id) {
    return DataResponse.ok(service.getDepartmentIds(id));
  }

  @Override
  public Response setDepartmentIds(Long id, DataRequest<List<Long>> request) {
    service.setDepartmentIds(id, request.getData());
    return Response.SUCCESS;
  }
}
