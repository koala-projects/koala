package cn.koala.task.domain;

import org.springframework.scheduling.Trigger;

/**
 * 任务触发器工厂接口
 *
 * @author Houtaroy
 */
public interface TaskTriggerFactory {

  Trigger from(Task task);
}
