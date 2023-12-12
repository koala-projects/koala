package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskExecuteResult;
import cn.koala.task.TaskExecutor;
import cn.koala.task.TaskInstanceFactory;
import cn.koala.task.TaskLog;
import cn.koala.task.TaskLogService;
import cn.koala.task.TaskTriggerFactory;
import cn.koala.util.Assert;
import cn.koala.util.LocalDateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 默认任务执行器
 * <p>
 * 会使用日志包装器包装任务实例, 以记录任务执行日志
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class DefaultTaskExecutor implements TaskExecutor {

  private final Map<String, ScheduledFuture<?>> instances = new ConcurrentReferenceHashMap<>();

  private final TaskInstanceFactory instanceFactory;
  private final TaskTriggerFactory triggerFactory;
  private final TaskScheduler scheduler;
  private final TaskLogService logService;

  @Override
  public void schedule(Task task) {
    Assert.notNull(task, "任务不存在");
    String key = task.getName();
    Assert.isTrue(!instances.containsKey(key), "任务已在计划中");
    Runnable instance = instanceFactory.from(task);
    Assert.notNull(instance, "任务实例创建失败");
    ScheduledFuture<?> future = scheduler.schedule(
      new TaskLogWrapper(task, TaskLog.Execution.AUTO),
      triggerFactory.from(task)
    );
    instances.put(key, future);
  }

  @Override
  public void cancel(Task task) {
    Assert.notNull(task, "任务不存在");
    String key = task.getName();
    ScheduledFuture<?> instance = instances.get(key);
    if (instance != null) {
      instance.cancel(false);
      instances.remove(key);
    }
  }

  @Override
  public TaskExecuteResult execute(Task task) {
    Assert.notNull(task, "任务不存在");
    Runnable instance = instanceFactory.from(task);
    Assert.notNull(instance, "任务实例创建失败");
    return new TaskLogWrapper(task, TaskLog.Execution.MANUAL).doRun();
  }

  @RequiredArgsConstructor
  private class TaskLogWrapper implements Runnable {

    private final Task task;

    private final TaskLog.Execution execution;

    @Override
    public void run() {
      this.doRun();
    }

    public TaskExecuteResult doRun() {
      Runnable instance = instanceFactory.from(task);
      TaskLog log = TaskLogEntity.from(task, execution);
      try {
        LOGGER.info("任务[name = {}]开始执行", task.getName());
        instance.run();
        log.setTaskStatus(TaskLog.Status.SUCCESS);
        LOGGER.info("任务[name = {}]执行成功", task.getName());
        return TaskExecuteResult.SUCCESS;
      } catch (Exception e) {
        log.setTaskStatus(TaskLog.Status.FAIL);
        log.setTaskError(e.getLocalizedMessage());
        LOGGER.error("任务[name = {}]执行失败", task.getName(), e);
        return TaskExecuteResult.from(e);
      } finally {
        log.setEndTime(LocalDateTimeUtils.toDate());
        logService.create(log);
      }
    }
  }
}
