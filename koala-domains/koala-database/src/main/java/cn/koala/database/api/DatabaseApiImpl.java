package cn.koala.database.api;

import cn.koala.database.domain.Database;
import cn.koala.database.domain.DatabaseEntity;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.database.service.DatabaseService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据库接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DatabaseApiImpl implements DatabaseApi {
  protected final DatabaseService service;

  @Override
  public DataResponse<Page<Database>> page(Map<String, Object> parameters, Pageable pageable) {
    return DataResponse.ok(service.page(parameters, pageable));
  }

  @Override
  public DataResponse<Database> load(Long id) {
    return DataResponse.ok(service.load(id));
  }

  @Override
  public DataResponse<Database> create(DatabaseEntity entity) {
    service.create(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, DatabaseEntity entity) {
    entity.setId(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(DatabaseEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<DatabaseTable>> listTables(Long id) {
    return DataResponse.ok(service.listTable(id));
  }

  @Override
  public DataResponse<DatabaseTable> loadTable(Long id, String name) {
    return DataResponse.ok(service.loadTable(id, name));
  }

  @Override
  public DataResponse<Boolean> connect(DatabaseEntity entity) {
    return DataResponse.ok(service.isConnectable(entity));
  }
}
