package cn.koala.task.autoconfigure;

import cn.koala.task.api.DefaultTaskApi;
import cn.koala.task.api.DefaultTaskLogApi;
import cn.koala.task.api.TaskApi;
import cn.koala.task.api.TaskLogApi;
import cn.koala.task.domain.BeanTaskInstanceFactory;
import cn.koala.task.domain.DefaultTaskExecutor;
import cn.koala.task.domain.SchedulingTaskTriggerFactory;
import cn.koala.task.domain.TaskApplicationRunner;
import cn.koala.task.domain.TaskExecutor;
import cn.koala.task.domain.TaskInstanceFactory;
import cn.koala.task.domain.TaskTriggerFactory;
import cn.koala.task.repository.TaskLogRepository;
import cn.koala.task.repository.TaskRepository;
import cn.koala.task.service.DefaultTaskLogService;
import cn.koala.task.service.DefaultTaskService;
import cn.koala.task.service.TaskLogService;
import cn.koala.task.service.TaskService;
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
    return new BeanTaskInstanceFactory(applicationContext);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskTriggerFactory taskTriggerFactory() {
    return new SchedulingTaskTriggerFactory();
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskExecutor taskExecutor(TaskInstanceFactory taskFactory, TaskTriggerFactory triggerFactory,
                                   TaskScheduler taskScheduler, TaskLogService taskLogService) {

    return new DefaultTaskExecutor(taskFactory, triggerFactory, taskScheduler, taskLogService);
  }

  @Bean
  @ConditionalOnMissingBean
  public TaskService taskService(TaskRepository taskRepository, TaskExecutor executor) {
    return new DefaultTaskService(taskRepository, executor);
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
