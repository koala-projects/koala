package cn.koala.system.api;

import cn.koala.log.annotation.Log;
import cn.koala.system.model.Department;
import cn.koala.system.model.DepartmentEntity;
import cn.koala.system.service.DepartmentService;
import cn.koala.util.TreeNode;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门管理接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DepartmentApiImpl implements DepartmentApi {
  protected final DepartmentService service;

  @Override
  @Log(module = "部门管理", content = "查询部门树")
  public DataResponse<List<TreeNode>> tree() {
    return DataResponse.ok(service.tree());
  }

  @Override
  @Log(module = "部门管理", content = "查看部门[id=${#id}]")
  public DataResponse<Department> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  @Log(module = "部门管理", content = "创建部门[name=${#entity.name}]")
  public DataResponse<Department> create(DepartmentEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  @Log(module = "部门管理", content = "更新部门[id=${#id}]")
  public Response update(Long id, DepartmentEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  @Log(module = "部门管理", content = "删除部门[id=${#id}]")
  public Response delete(Long id) {
    service.delete(DepartmentEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
