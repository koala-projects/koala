package cn.koala.query.support;

import cn.koala.query.Query;
import cn.koala.query.QueryApi;
import cn.koala.query.QueryService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 查询接口实现类
 *
 * @author Koala Code Generator
 */
@RequiredArgsConstructor
@RestController
public class DefaultQueryApi implements QueryApi {

  private final QueryService service;

  @Override
  public DataResponse<Page<Query>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Query> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Query> create(Query entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, Query entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(QueryEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<Page<Map<String, Object>>> execute(Long id, Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.execute(id, parameters, pageable));
  }
}
