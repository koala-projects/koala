package cn.koala.database.apis;

import cn.koala.database.Database;
import cn.koala.database.DatabaseTable;
import cn.koala.database.entities.DatabaseEntity;
import cn.koala.database.services.DatabaseService;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
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
  public DataResponse<Database> add(DatabaseEntity entity) {
    service.add(entity);
    return DataResponse.ok(entity);
  }

  @Override
  public Response update(Long id, DatabaseEntity entity) {
    entity.setIdIfAbsent(id);
    service.update(entity);
    return Response.SUCCESS;
  }

  @Override
  public Response delete(Long id) {
    service.delete(DatabaseEntity.builder().id(id).build());
    return Response.SUCCESS;
  }

  @Override
  public DataResponse<List<DatabaseTable>> tables(Long id) {
    Database database = service.load(id);
    Assert.notNull(database, "数据库不存在");
    return DataResponse.ok(service.getTables(database));
  }

  @Override
  public DataResponse<DatabaseTable> tables(Long id, String name) {
    Assert.isTrue(StringUtils.hasLength(name), "表名不能为空");
    Database database = service.load(id);
    Assert.notNull(database, "数据库不存在");
    return DataResponse.ok(service.getTable(database, name));
  }

  @Override
  public DataResponse<Boolean> connect(DatabaseEntity entity) {
    return DataResponse.ok(service.isConnectable(entity));
  }
}
