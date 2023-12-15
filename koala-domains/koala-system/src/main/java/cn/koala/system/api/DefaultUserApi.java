package cn.koala.system.api;

import cn.koala.log.annotation.Log;
import cn.koala.system.domain.User;
import cn.koala.system.domain.UserEntity;
import cn.koala.system.service.UserService;
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
public class DefaultUserApi implements UserApi {

  private final UserService service;

  @Override
  @Log(module = "用户管理", content = "查询用户列表")
  public DataResponse<Page<User>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  @Log(module = "用户管理", content = "查看用户[id=${#id}]")
  public DataResponse<User> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  @Log(module = "用户管理", content = "创建用户[username=${#user.username}]")
  public DataResponse<User> create(UserEntity user) {
    service.create(user);
    return DataResponse.ok(user);
  }

  @Override
  @Log(module = "用户管理", content = "更新用户[id=${#id}]")
  public Response update(Long id, UserEntity user) {
    user.setId(id);
    service.update(user);
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "用户管理", content = "删除用户[id=${#id}]")
  public Response delete(Long id) {
    service.delete(UserEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "用户管理", content = "获取用户[id=${#id}]角色id列表")
  public DataResponse<List<Long>> getRoleIds(Long id) {
    return DataResponse.ok(service.getRoleIds(id));
  }

  @Override
  @Log(module = "用户管理", content = "设置用户[id=${#id}]角色")
  public Response setRoleIds(Long id, DataRequest<List<Long>> request) {
    service.setRoleIds(id, request.getData());
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "用户管理", content = "获取用户[id=${#id}]部门id列表")
  public DataResponse<List<Long>> getDepartmentIds(Long id) {
    return DataResponse.ok(service.getDepartmentIds(id));
  }

  @Override
  @Log(module = "用户管理", content = "查看用户[id=${#id}]部门")
  public Response setDepartmentIds(Long id, DataRequest<List<Long>> request) {
    service.setDepartmentIds(id, request.getData());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<Long>> listDutyIds(Long id) {
    return DataResponse.ok(service.listDutyIds(id));
  }

  @Override
  public Response setUserDuties(Long id, DataRequest<List<Long>> request) {
    service.updateDuties(id, request.getData());
    return Response.SUCCESS;
  }
}
