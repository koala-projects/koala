#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

import ${package}.entity.ExampleEntity;
import ${package}.service.ExampleService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 示例表接口实现
 *
 * @author koala web application
 */
@RestController
@RequiredArgsConstructor
public class ExampleApiImpl implements ExampleApi {

  private final ExampleService service;

  @Override
  public DataResponse<Page<ExampleEntity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<ExampleEntity> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<ExampleEntity> create(ExampleEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, ExampleEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(ExampleEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}