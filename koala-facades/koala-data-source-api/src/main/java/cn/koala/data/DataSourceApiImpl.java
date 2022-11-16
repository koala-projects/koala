package cn.koala.data;

import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@RestController
@RequiredArgsConstructor
public class DataSourceApiImpl implements DataSourceApi {

  protected final DataSourceService service;

  @Override
  public DataResponse<Page<DataSource>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.list(parameters, pageable));
  }

  @Override
  public DataResponse<DataSource> loadById(String id) {
    return DataResponse.ok(service.load(id).orElse(null));
  }

  @Override
  public DataResponse<DataSource> create(DataSourceEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(String id, DataSourceEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(String id) {
    service.delete(DataSourceEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<String>> getCatalogNames(String id) {
    return DataResponse.ok(service.load(id).map(service::getCatalogNames).orElse(List.of()));
  }

  @Override
  public DataResponse<List<String>> getTableNames(String id, String catalogName) {
    return DataResponse.ok(service.load(id)
      .map(dataSource -> service.getTableNames(dataSource, catalogName))
      .orElse(List.of()));
  }

  @Override
  public DataResponse<Boolean> isConnectable(DataSourceEntity entity) {
    return DataResponse.ok(service.isConnectable(entity));
  }
}
