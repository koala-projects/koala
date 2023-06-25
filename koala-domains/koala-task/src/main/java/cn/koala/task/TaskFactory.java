package cn.koala.task;

/**
 * 任务工厂接口
 *
 * @author Houtaroy
 */
public interface TaskFactory {

  Runnable create(Task task);
}
