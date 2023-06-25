package cn.koala.task.support;

import cn.koala.task.TaskManager;
import cn.koala.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.Map;

/**
 * 任务应用启动器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class TaskApplicationRunner implements ApplicationRunner {

  private final TaskService service;

  private final TaskManager manager;

  @Override
  public void run(ApplicationArguments args) {
    service.list(Map.of()).forEach(manager::start);
  }
}
