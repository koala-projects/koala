package cn.koala.task;

/**
 * 任务工厂接口
 *
 * @author Houtaroy
 */
public interface TaskInstanceFactory {

  Runnable from(Task task);
}
