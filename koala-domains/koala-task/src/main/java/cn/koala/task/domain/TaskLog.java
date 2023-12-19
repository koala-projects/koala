package cn.koala.task.domain;

import org.springframework.data.domain.Persistable;

import java.util.Date;

/**
 * 任务日志接口
 *
 * @author Houtaroy
 */
public interface TaskLog extends Persistable<Long> {

  Long getTaskId();

  TaskMode getTaskMode();

  void setTaskMode(TaskMode taskMode);

  TaskStatus getTaskStatus();

  void setTaskStatus(TaskStatus taskStatus);

  String getTaskError();

  void setTaskError(String taskError);

  Date getStartTime();

  Date getEndTime();

  void setEndTime(Date endTime);
}
