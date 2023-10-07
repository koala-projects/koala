package cn.koala.task.support;

import cn.koala.persist.domain.YesNo;
import cn.koala.task.Task;
import cn.koala.task.TaskApi;
import cn.koala.task.TaskExecuteResult;
import cn.koala.task.TaskExecutor;
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
  private final TaskExecutor executor;

  @Override
  public DataResponse<Page<Task>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Task> load(Long id) {
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
  public Response enable(Long id) {
    service.update(TaskEntity.builder().id(id).isEnabled(YesNo.YES).build());
    return Response.SUCCESS;
  }

  @Override
  public Response disable(Long id) {
    service.update(TaskEntity.builder().id(id).isEnabled(YesNo.NO).build());
    return Response.SUCCESS;
  }

  @Override
  public Response execute(Long id) {
    TaskExecuteResult result = executor.execute(service.load(id));
    return result.isSucceed() ? Response.SUCCESS : Response.error(result.getMessage());
  }
}
