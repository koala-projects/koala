package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskTriggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.time.Duration;

/**
 * 基于{@link Trigger Trigger}的任务触发器工厂
 * <p>
 * 仅支持Cron和Duration表达式
 *
 * @author Houtaroy
 */
public class TaskSchedulingTriggerFactory implements TaskTriggerFactory {

  @Override
  public Trigger from(Task task) {
    String expression = task.getTriggerConfig();
    return isDuration(expression) ? new PeriodicTrigger(Duration.parse(expression)) : new CronTrigger(expression);
  }

  private boolean isDuration(String expression) {
    return expression.startsWith("P") || expression.startsWith("-P");
  }
}
