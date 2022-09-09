package cn.koala.database;

import java.util.List;

/**
 * 数据库服务定义
 *
 * @author Houtaroy
 */
public interface DatabaseService {
  /**
   * 获取数据库全部表
   *
   * @param connectProperties 连接属性
   * @return 数据库全部表
   */
  List<Table> getTables(ConnectProperties connectProperties);
}
