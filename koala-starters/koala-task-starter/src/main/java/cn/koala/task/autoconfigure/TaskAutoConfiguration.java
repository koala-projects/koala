package cn.koala.task.autoconfigure;

import cn.koala.task.TaskApi;
import cn.koala.task.TaskExecutor;
import cn.koala.task.TaskInstanceFactory;
import cn.koala.task.TaskLogApi;
import cn.koala.task.TaskLogService;
import cn.koala.task.TaskService;
import cn.koala.task.TaskTriggerFactory;
import cn.koala.task.repository.TaskLogRepository;
import cn.koala.task.repository.TaskRepository;
import cn.koala.task.support.DefaultTaskApi;
import cn.koala.task.support.DefaultTaskExecutor;
import cn.koala.task.support.DefaultTaskLogApi;
import cn.koala.task.support.DefaultTaskLogService;
import cn.koala.task.support.DefaultTaskService;
import cn.koala.task.support.TaskApplicationRunner;
import cn.koala.task.support.TaskListener;
import cn.koala.task.support.TaskSchedulingTriggerFactory;
import cn.koala.task.support.TaskSpringBeanInstanceFactory;
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
  public TaskInstanceFactory taskInstanceFactory(ApplicationContext applicationContext) {
    return new TaskSpringBeanInstanceFactory(applicationContext);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskTriggerFactory taskTriggerFactory() {
    return new TaskSchedulingTriggerFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskExecutor taskExecutor(TaskInstanceFactory taskFactory, TaskTriggerFactory triggerFactory,
                                   TaskScheduler taskScheduler, TaskLogService taskLogService) {
    
    return new DefaultTaskExecutor(taskFactory, triggerFactory, taskScheduler, taskLogService);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskListener taskListener(TaskRepository taskRepository, TaskExecutor taskExecutor) {
    return new TaskListener(taskRepository, taskExecutor);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskService taskService(TaskRepository taskRepository) {
    return new DefaultTaskService(taskRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskApi TaskApi(TaskService taskService, TaskExecutor taskExecutor) {
    return new DefaultTaskApi(taskService, taskExecutor);
  }

  @Bean
  public TaskApplicationRunner taskApplicationRunner(TaskService taskService, TaskExecutor taskExecutor) {
    return new TaskApplicationRunner(taskService, taskExecutor);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskLogService taskLogService(TaskLogRepository logRepository) {
    return new DefaultTaskLogService(logRepository);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskLogApi taskLogApi(TaskLogService logService) {
    return new DefaultTaskLogApi(logService);
  }
}
