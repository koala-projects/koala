package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskExecutor;
import cn.koala.task.TaskFactory;
import cn.koala.task.TaskLog;
import cn.koala.task.TaskLogService;
import cn.koala.task.TriggerFactory;
import cn.koala.toolkit.DateHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.util.Assert;
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

  private final TaskFactory taskFactory;
  private final TriggerFactory triggerFactory;
  private final TaskScheduler scheduler;
  private final TaskLogService logService;

  @Override
  public void schedule(Task task) {
    Assert.notNull(task, "任务不能为空");
    String key = task.getName();
    Assert.isTrue(!instances.containsKey(key), "任务[name = %s]已在运行".formatted(key));
    Runnable instance = taskFactory.create(task);
    Assert.notNull(instance, "任务[name = %s]实例创建失败".formatted(key));
    instances.put(key, scheduler.schedule(new LogWrapper(task, instance, TaskLog.Execution.AUTO), triggerFactory.create(task)));
  }

  @Override
  public void cancel(Task task) {
    Assert.notNull(task, "任务不能为空");
    String key = task.getName();
    ScheduledFuture<?> instance = instances.get(key);
    if (instance != null) {
      instance.cancel(false);
      instances.remove(key);
    }
  }

  @Override
  public void execute(Task task) {
    Assert.notNull(task, "任务不能为空");
    Runnable instance = taskFactory.create(task);
    Assert.notNull(instance, "任务[name = %s]实例创建失败".formatted(task.getName()));
    new LogWrapper(task, taskFactory.create(task), TaskLog.Execution.MANUAL).run();
  }

  @RequiredArgsConstructor
  public class LogWrapper implements Runnable {

    private final Task task;

    private final Runnable instance;

    private final TaskLog.Execution execution;

    @Override
    public void run() {
      TaskLog log = obtainTaskLog(task, execution);
      try {
        LOGGER.info("任务[name = {}]开始执行", task.getName());
        this.instance.run();
        log.setTaskStatus(TaskLog.Status.SUCCESS);
        LOGGER.info("任务[name = {}]执行成功", task.getName());
      } catch (Exception e) {
        log.setTaskStatus(TaskLog.Status.FAIL);
        log.setTaskError(e.getLocalizedMessage());
        LOGGER.error("任务[name = {}]执行失败", task.getName(), e);
      } finally {
        log.setEndTime(DateHelper.now());
        logService.create(log);
      }
    }

    private TaskLog obtainTaskLog(Task task, TaskLog.Execution execution) {
      return TaskLogEntity.builder()
        .taskId(task.getId())
        .execution(execution)
        .startTime(DateHelper.now())
        .build();
    }
  }
}
