#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.apis;

import ${package}.entities.ExampleEntity;
import ${package}.services.ExampleService;
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
 * @author Eucalyptus Generator
 */
@RestController
@RequiredArgsConstructor
public class ExampleApiImpl implements ExampleApi {

  private final ExampleService service;

  @Override
  public DataResponse<Page<ExampleEntity>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }

  @Override
  public DataResponse<ExampleEntity> load(String id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }

  @Override
  public DataResponse<ExampleEntity> create(ExampleEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(String id, ExampleEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    service.delete(ExampleEntity.builder().id(id).build());
    return Response.SUCCESS;
  }
}
