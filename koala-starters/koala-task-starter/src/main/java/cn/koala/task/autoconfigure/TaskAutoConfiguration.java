package cn.koala.task.autoconfigure;

import cn.koala.task.TaskApi;
import cn.koala.task.TaskFactory;
import cn.koala.task.TaskManager;
import cn.koala.task.TaskService;
import cn.koala.task.TriggerFactory;
import cn.koala.task.repository.TaskRepository;
import cn.koala.task.support.DefaultTaskApi;
import cn.koala.task.support.DefaultTaskFactory;
import cn.koala.task.support.DefaultTaskManager;
import cn.koala.task.support.DefaultTaskService;
import cn.koala.task.support.DefaultTriggerFactory;
import cn.koala.task.support.TaskApplicationRunner;
import cn.koala.task.support.TaskListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 任务自动配置类
 *
 * @author Houtaroy
 */
@Configuration
@EnableScheduling
@MapperScan(basePackages = "cn.koala.task.repository")
public class TaskAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public TaskFactory taskFactory(ApplicationContext applicationContext) {
    return new DefaultTaskFactory(applicationContext);
  }

  @Bean
  @ConditionalOnMissingBean
  public TriggerFactory triggerFactory() {
    return new DefaultTriggerFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskManager taskManager(TaskFactory taskFactory, TriggerFactory triggerFactory, TaskScheduler taskScheduler) {
    return new DefaultTaskManager(taskFactory, triggerFactory, taskScheduler);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskListener taskListener(TaskRepository taskRepository, TaskManager taskManager) {
    return new TaskListener(taskRepository, taskManager);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskService taskService(TaskRepository taskRepository) {
    return new DefaultTaskService(taskRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskApi TaskApi(TaskService taskService, TaskManager taskManager) {
    return new DefaultTaskApi(taskService, taskManager);
  }

  @Bean
  public TaskApplicationRunner taskApplicationRunner(TaskService taskService, TaskManager taskManager) {
    return new TaskApplicationRunner(taskService, taskManager);
  }
}
