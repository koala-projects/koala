package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskFactory;
import org.springframework.context.ApplicationContext;

/**
 * 默认任务工厂
 * <p>
 * 从Spring IOC容器中获取任务实例
 *
 * @author Houtaroy
 */
public class DefaultTaskFactory implements TaskFactory {

  private final ApplicationContext context;

  public DefaultTaskFactory(ApplicationContext context) {
    this.context = context;
  }

  @Override
  public Runnable create(Task task) {
    return context.getBean(task.getTaskConfig(), Runnable.class);
  }
}
