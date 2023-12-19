package cn.koala.task.domain;


/**
 * 任务执行器
 *
 * @author Houtaroy
 */
public interface TaskExecutor {

  void schedule(Task task);

  void cancel(Task task);

  TaskExecuteResult execute(Task task);
}
