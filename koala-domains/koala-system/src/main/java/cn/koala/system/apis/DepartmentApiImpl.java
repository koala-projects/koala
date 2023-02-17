package cn.koala.system.apis;

import cn.koala.system.Department;
import cn.koala.system.entities.DepartmentEntity;
import cn.koala.system.services.DepartmentService;
import cn.koala.toolkit.tree.TreeNode;
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
  public DataResponse<List<TreeNode>> tree() {
    return DataResponse.ok(service.tree());
  }

  @Override
  public DataResponse<Department> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Department> create(DepartmentEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, DepartmentEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(DepartmentEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
