package cn.koala.task.support;

import cn.koala.task.TaskLog;
import cn.koala.task.TaskLogApi;
import cn.koala.task.TaskLogService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 任务日志接口实现类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@RestController
public class DefaultTaskLogApi implements TaskLogApi {

  protected final TaskLogService service;

  @Override
  public DataResponse<Page<TaskLog>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<TaskLog> load(Long id) {
    return DataResponse.ok(service.load(id));
  }
}
