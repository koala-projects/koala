package cn.koala.datamodel;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 数据接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DataApiImpl implements DataApi {
  protected final DataService service;

  @Override
  public DataResponse<Page<Map<String, Object>>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }

  @Override
  public DataResponse<Map<String, Object>> load(String id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }

  @Override
  public Response create(CreateDataRequest createDataRequest) {
    service.add(createDataRequest.getMetadata(), createDataRequest.getData());
    return Response.SUCCESS;
  }

  @Override
  public Response update(String id, Map<String, Object> data) {
    service.update(id, data);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    service.delete(id);
    return Response.SUCCESS;
  }
}
