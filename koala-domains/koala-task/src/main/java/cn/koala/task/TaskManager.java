package cn.koala.task;

/**
 * 任务管理器接口
 *
 * @author Houtaroy
 */
public interface TaskManager {

  void start(Task task);

  void stop(Task task);
}
