package cn.koala.security.log.api;

import cn.koala.security.log.LoginLog;
import cn.koala.security.log.LoginLogService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 默认登录日志接口
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DefaultLoginLogApi implements LoginLogApi {

  private final LoginLogService service;

  @Override
  public DataResponse<Page<LoginLog>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<LoginLog> load(Long id) {
    return DataResponse.ok(service.load(id));
  }
}
