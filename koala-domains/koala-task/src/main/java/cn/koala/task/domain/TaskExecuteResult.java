package cn.koala.task.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 任务执行结果类
 *
 * @author Houtaroy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskExecuteResult {

  public static TaskExecuteResult SUCCESS = new TaskExecuteResult(TaskStatus.FINISH, "执行成功");

  private TaskStatus status;

  private String message;

  public static TaskExecuteResult from(Exception e) {
    return new TaskExecuteResult(TaskStatus.ERROR, e.getLocalizedMessage());
  }
}
