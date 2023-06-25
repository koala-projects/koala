package cn.koala.task.support;

import cn.koala.task.TaskApi;
import cn.koala.task.TaskManager;
import cn.koala.task.TaskService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 任务接口实现类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@RestController
public class DefaultTaskApi implements TaskApi {

  private final TaskService service;
  private final TaskManager manager;

  @Override
  public DataResponse<Page<TaskEntity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<TaskEntity> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<TaskEntity> create(TaskEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, TaskEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(TaskEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public Response start(Long id) {
    manager.start(service.load(id));
    return Response.SUCCESS;
  }

  @Override
  public Response stop(Long id) {
    manager.stop(service.load(id));
    return Response.SUCCESS;
  }
}
