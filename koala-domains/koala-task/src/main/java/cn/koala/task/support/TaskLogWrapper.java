package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskLog;
import cn.koala.task.TaskLogService;
import cn.koala.toolkit.DateHelper;
import lombok.RequiredArgsConstructor;

/**
 * 任务日志包装器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class TaskLogWrapper implements Runnable {

  private final Task task;
  private final Runnable instance;
  private final TaskLogService logService;

  @Override
  public void run() {
    TaskLogEntity log = TaskLogEntity.builder()
      .taskId(this.task.getId())
      .taskStatus(TaskLog.Status.RUNNING)
      .startTime(DateHelper.now())
      .build();
    this.logService.create(log);
    try {
      this.instance.run();
      log.setTaskStatus(TaskLog.Status.SUCCESS);
    } catch (Exception e) {
      log.setTaskError(e.getLocalizedMessage());
      log.setTaskStatus(TaskLog.Status.FAIL);
    } finally {
      log.setEndTime(DateHelper.now());
      this.logService.update(log);
    }
  }
}
