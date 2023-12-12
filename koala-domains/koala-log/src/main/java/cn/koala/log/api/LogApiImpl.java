package cn.koala.log.api;

import cn.koala.log.Log;
import cn.koala.log.service.LogService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 字典接口实现
 *
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class LogApiImpl implements LogApi {
  protected final LogService service;

  @Override
  public DataResponse<Page<Log>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Log> load(Long id) {
    return DataResponse.ok(service.load(id));
  }
}
