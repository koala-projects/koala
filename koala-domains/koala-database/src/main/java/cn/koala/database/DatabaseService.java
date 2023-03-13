package cn.koala.database;

import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;

import java.util.List;

/**
 * 数据库服务接口
 *
 * @author Houtaroy
 */
public interface DatabaseService extends CrudService<Database, Long>, PagingService<Database, Long> {
  List<? extends DatabaseTable> getTables(Database database);

  DatabaseTable getTable(Database database, String tableName);
}
