package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskFactory;
import cn.koala.task.TaskManager;
import cn.koala.task.TriggerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 默认任务管理器
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultTaskManager implements TaskManager {

  private final Map<String, ScheduledFuture<?>> instances = new ConcurrentReferenceHashMap<>();

  private final TaskFactory taskFactory;
  private final TriggerFactory triggerFactory;
  private final TaskScheduler scheduler;

  @Override
  public void start(Task task) {
    Assert.notNull(task, "任务不能为空");
    String key = task.getName();
    Assert.isTrue(!instances.containsKey(key), "任务[name = %s]已在运行".formatted(key));
    instances.put(key, scheduler.schedule(taskFactory.create(task), triggerFactory.create(task)));
  }

  @Override
  public void stop(Task task) {
    Assert.notNull(task, "任务不能为空");
    String key = task.getName();
    ScheduledFuture<?> instance = instances.get(key);
    if (instance != null) {
      instance.cancel(false);
      instances.remove(key);
    }
  }
}
