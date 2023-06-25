package cn.koala.task;

import org.springframework.scheduling.Trigger;

/**
 * 触发器工厂接口
 *
 * @author Houtaroy
 */
public interface TriggerFactory {

  Trigger create(Task task);
}
