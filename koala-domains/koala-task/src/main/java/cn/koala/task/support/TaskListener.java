package cn.koala.task.support;

import cn.koala.persist.domain.YesNo;
import cn.koala.persist.listener.support.AbstractInheritedEntityListener;
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
 * <p>
 * 用于监听任务实体的增删改事件, 并调用任务管理器进行任务的启动和停止
 *
 * @author Houtaroy
 */
@Order(100)
@RequiredArgsConstructor
public class TaskListener extends AbstractInheritedEntityListener<Task> {

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
