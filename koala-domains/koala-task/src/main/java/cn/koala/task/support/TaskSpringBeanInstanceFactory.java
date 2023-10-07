package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskInstanceFactory;
import org.springframework.context.ApplicationContext;

/**
 * 基于 Spring Bean 的任务工厂
 * <p>
 * 从Spring IOC容器中获取任务实例
 *
 * @author Houtaroy
 */
public class TaskSpringBeanInstanceFactory implements TaskInstanceFactory {

  private final ApplicationContext context;

  public TaskSpringBeanInstanceFactory(ApplicationContext context) {
    this.context = context;
  }

  @Override
  public Runnable from(Task task) {
    return context.getBean(task.getTaskConfig(), Runnable.class);
  }
}
