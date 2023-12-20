package cn.koala.task.domain;

import cn.koala.data.domain.YesNo;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;

/**
 * 任务调度实体监听器
 * <p>
 * 用于在增删改之后调度任务
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class TaskSchedulingEntityListener {

  private final TaskExecutor executor;

  @PostPersist
  public void postPersist(Task entity) {
    if (entity.getEnabled() == YesNo.YES) {
      executor.schedule(entity);
    }
  }

  @PostUpdate
  public void postUpdate(Task entity) {
    if (entity.getEnabled() != null) {
      executor.cancel(entity);
      if (entity.getEnabled() == YesNo.YES) {
        executor.schedule(entity);
      }
    }
  }

  @PostRemove
  public void postRemove(Task entity) {
    executor.cancel(entity);
  }
}
