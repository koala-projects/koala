package cn.koala.task.support;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.task.TaskService;
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
public class DefaultTaskService extends AbstractMyBatisService<TaskEntity, Long> implements TaskService {

  private final TaskRepository repository;
}
