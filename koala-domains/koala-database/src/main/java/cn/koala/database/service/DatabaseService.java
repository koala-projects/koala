package cn.koala.database.service;

import cn.koala.data.service.CrudService;
import cn.koala.database.domain.Database;
import cn.koala.database.domain.DatabaseTable;

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
