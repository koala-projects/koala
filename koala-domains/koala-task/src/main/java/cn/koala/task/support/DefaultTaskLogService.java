package cn.koala.task.support;

import cn.koala.mybatis.AbstractMyBatisService;
import cn.koala.task.TaskLog;
import cn.koala.task.TaskLogService;
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
public class DefaultTaskLogService extends AbstractMyBatisService<TaskLog, Long> implements TaskLogService {

  private final TaskLogRepository repository;
}
