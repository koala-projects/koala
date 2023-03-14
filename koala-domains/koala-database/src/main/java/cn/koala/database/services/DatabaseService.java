package cn.koala.database.services;

import cn.koala.database.Database;
import cn.koala.database.DatabaseTable;
import cn.koala.mybatis.CrudService;
import cn.koala.mybatis.PagingService;

import java.util.List;

/**
 * 数据库服务接口
 *
 * @author Houtaroy
 */
public interface DatabaseService extends CrudService<Database, Long>, PagingService<Database, Long> {
  List<DatabaseTable> getTables(Database database);

  DatabaseTable getTable(Database database, String table);
}
