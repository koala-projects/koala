package cn.koala.task.support;

import cn.koala.persist.domain.YesNo;
import cn.koala.task.TaskExecutor;
import cn.koala.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.Map;

/**
 * 任务应用启动器
 *
 * @author Houtaroy
 */
@Order(3400)
@RequiredArgsConstructor
public class TaskApplicationRunner implements ApplicationRunner {

  private final TaskService service;

  private final TaskExecutor executor;

  @Override
  public void run(ApplicationArguments args) {
    service.list(Map.of("isEnabled", YesNo.YES.getValue())).forEach(executor::schedule);
  }
}
