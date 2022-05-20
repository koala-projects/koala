package cn.koala.jdbc;

import java.util.List;

/**
 * @author Houtaroy
 */
public interface DatabaseService {
  /**
   * 根据连接属性获取数据库表
   *
   * @param properties 连接属性
   * @return 数据库表列表
   * @throws Exception 异常
   */
  List<Table> tables(ConnectionProperties properties) throws Exception;
}
