package cn.koala.database.services;

import cn.koala.database.Table;

import java.util.List;
import java.util.Map;

/**
 * 数据库表服务接口
 *
 * @author Houtaroy
 */
public interface TableService {
  List<Table> list(Map<String, Object> parameters);

  Table load(String name);
}
