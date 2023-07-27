package cn.koala.task;

/**
 * 任务管理器接口
 *
 * @author Houtaroy
 */
public interface TaskManager {

  void schedule(Task task);

  void cancel(Task task);

  void run(Task task);
}