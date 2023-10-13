package cn.koala.task.support;

import cn.koala.persist.domain.YesNo;
import cn.koala.task.Task;
import cn.koala.task.TaskExecutor;
import cn.koala.task.repository.TaskRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

/**
 * 任务实体监听器
 *
 * @author Houtaroy
 */
@Order(1000)
@RequiredArgsConstructor
public class TaskEntityListener {

  private final TaskRepository repository;

  private final TaskExecutor executor;

  @PostPersist
  public void postPersist(Task entity) {
    if (entity.getIsEnabled() == YesNo.YES) {
      executor.schedule(entity);
    }
  }

  @PostUpdate
  public void postUpdate(Task entity) {
    repository.load(entity.getId())
      .ifPresent(persist -> {
        executor.cancel(persist);
        if (persist.getIsEnabled() == YesNo.YES) {
          executor.schedule(persist);
        }
      });
  }

  @PreRemove
  public void preRemove(Task entity) {
    repository.load(entity.getId())
      .ifPresent(executor::cancel);
  }
}
