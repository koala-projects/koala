package cn.koala.task.domain;


/**
 * 任务工厂接口
 *
 * @author Houtaroy
 */
public interface TaskInstanceFactory {

  Runnable from(Task task);
}
