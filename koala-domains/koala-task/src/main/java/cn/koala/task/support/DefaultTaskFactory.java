package cn.koala.task.support;

import cn.koala.task.Task;
import cn.koala.task.TaskFactory;
import cn.koala.task.TaskLogService;
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

  private final TaskLogService logService;

  public DefaultTaskFactory(ApplicationContext context) {
    this.context = context;
    this.logService = context.getBean(TaskLogService.class);
  }

  @Override
  public Runnable create(Task task) {
    return new TaskLogWrapper(task, context.getBean(task.getTaskConfig(), Runnable.class), this.logService);
  }
}
