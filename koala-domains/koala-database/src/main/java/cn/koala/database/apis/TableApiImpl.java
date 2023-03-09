package cn.koala.database.apis;

import cn.koala.database.Table;
import cn.koala.database.services.TableService;
import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 数据库表接口实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class TableApiImpl implements TableApi {
  protected final TableService service;

  @Override
  public DataResponse<List<Table>> list(Map<String, Object> parameters) {
    return DataResponse.ok(service.list(parameters));
  }

  @Override
  public DataResponse<Table> load(String name) {
    return DataResponse.ok(service.load(name));
  }
}
