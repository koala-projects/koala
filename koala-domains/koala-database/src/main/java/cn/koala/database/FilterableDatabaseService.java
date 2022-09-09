package cn.koala.database;

import java.util.List;
import java.util.function.Predicate;

/**
 * 可过滤的数据库服务定义
 *
 * @author Houtaroy
 */
public interface FilterableDatabaseService extends DatabaseService {
  /**
   * 获取数据库表
   *
   * @param connectProperties 连接属性
   * @param filter            过滤器
   * @return 数据库全部表
   */
  default List<Table> getTables(ConnectProperties connectProperties, Predicate<Table> filter) {
    return getTables(connectProperties).stream().filter(filter).toList();
  }
}
