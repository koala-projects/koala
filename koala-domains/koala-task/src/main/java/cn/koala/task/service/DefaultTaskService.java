package cn.koala.task.service;

import cn.koala.data.domain.YesNo;
import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.task.domain.Task;
import cn.koala.task.domain.TaskExecutor;
import cn.koala.task.repository.TaskRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

/**
 * 任务服务类
 *
 * @author Koala Code Generator
 */
@Getter
@RequiredArgsConstructor
public class DefaultTaskService extends AbstractSmartService<Long, Task, Long> implements TaskService {

  private final TaskRepository repository;

  private final TaskExecutor executor;

  @Override
  public <S extends Task> void create(@NonNull S entity) {
    super.create(entity);
    if (entity.getEnabled() == YesNo.YES) {
      executor.schedule(entity);
    }
  }

  @Override
  public <S extends Task> void update(@NonNull S entity) {
    super.update(entity);
    if (entity.getEnabled() != null) {
      executor.cancel(entity);
      if (entity.getEnabled() == YesNo.YES) {
        executor.schedule(entity);
      }
    }
  }

  @Override
  public <S extends Task> void delete(@NonNull S entity) {
    super.delete(entity);
    executor.cancel(entity);
  }
}
