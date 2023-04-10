package cn.koala.database.services;

import cn.koala.database.Database;
import cn.koala.database.DatabaseTable;
import cn.koala.persist.CrudService;

import java.util.List;

/**
 * 数据库服务接口
 *
 * @author Houtaroy
 */
public interface DatabaseService extends CrudService<Database, Long> {
  List<DatabaseTable> getTables(Database database);

  DatabaseTable getTable(Database database, String table);

  boolean isConnectable(Database database);
}
