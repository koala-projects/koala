package cn.koala.builder;

import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 领域转换器接口
 *
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class DomainConverterApiImpl implements DomainConverterApi {

  private final DomainConverterService service;

  @Override
  public DataResponse<List<DomainConverter>> list() {
    return DataResponse.ok(service.list(Map.of()));
  }
}
