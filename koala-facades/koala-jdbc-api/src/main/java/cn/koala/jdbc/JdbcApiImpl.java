package cn.koala.jdbc;

import cn.koala.utils.ConnectionProperties;
import cn.koala.utils.JdbcTable;
import cn.koala.utils.JdbcUtil;
import cn.koala.web.DataResponse;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Houtaroy
 */
@Slf4j
public class JdbcApiImpl implements JdbcApi {
  @Override
  public DataResponse<List<JdbcTable>> tables(ConnectionProperties properties) {
    try {
      return DataResponse.ok(JdbcUtil.tables(properties));
    } catch (SQLException e) {
      LOGGER.error("查询数据库全部表失败", e);
      throw new RuntimeException("查询失败");
    }
  }
}
