package cn.koala.database.services;

import cn.koala.database.Database;
import cn.koala.database.DatabaseTable;
import cn.koala.persist.service.CrudService;

import java.util.List;

/**
 * 数据库服务接口
 *
 * @author Houtaroy
 */
public interface DatabaseService extends CrudService<Database, Long> {

  boolean isConnectable(Database database);

  List<DatabaseTable> listTable(Long id);

  List<DatabaseTable> listTable(Long id, List<String> names);

  DatabaseTable loadTable(Long id, String name);
}
