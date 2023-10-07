package cn.koala.task;

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

  public static TaskExecuteResult SUCCESS = new TaskExecuteResult(true, "执行成功");

  boolean isSucceed;

  String message;

  public static TaskExecuteResult from(Exception e) {
    return new TaskExecuteResult(false, e.getLocalizedMessage());
  }
}
