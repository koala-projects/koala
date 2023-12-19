package cn.koala.task.service;

import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.task.domain.Task;
import cn.koala.task.repository.TaskRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 任务服务类
 *
 * @author Koala Code Generator
 */
@Getter
@RequiredArgsConstructor
public class DefaultTaskService extends AbstractSmartService<Long, Task, Long> implements TaskService {

  private final TaskRepository repository;
}
