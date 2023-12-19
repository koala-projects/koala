package cn.koala.task.service;

import cn.koala.mybatis.service.AbstractCrudService;
import cn.koala.task.domain.TaskLog;
import cn.koala.task.repository.TaskLogRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 任务日志服务类
 *
 * @author Koala Code Generator
 */
@Getter
@RequiredArgsConstructor
public class DefaultTaskLogService extends AbstractCrudService<TaskLog, Long> implements TaskLogService {

  private final TaskLogRepository repository;
}
