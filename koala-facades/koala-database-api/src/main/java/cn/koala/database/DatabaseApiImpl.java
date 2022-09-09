package cn.koala.database;

import cn.koala.web.DataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据库接口实现类
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
@RestController
public class DatabaseApiImpl implements DatabaseApi {

  protected final FilterableDatabaseService databaseService;

  @Override
  public DataResponse<List<Table>> tables(TablesRequest tablesRequest) {
    return DataResponse.ok(databaseService.getTables(tablesRequest, new PrefixFilter(tablesRequest.getPrefix())));
  }
}
