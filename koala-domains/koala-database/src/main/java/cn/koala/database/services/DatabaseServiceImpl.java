package cn.koala.database.services;

import cn.koala.database.ConnectionQuery;
import cn.koala.database.Database;
import cn.koala.database.DatabaseTable;
import cn.koala.database.SimpleDatabaseTable;
import cn.koala.database.SimpleDatabaseTableColumn;
import cn.koala.database.repositories.DatabaseRepository;
import cn.koala.mybatis.BaseService;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库服务实现类
 *
 * @author Houtaroy
 */
@Slf4j
public class DatabaseServiceImpl extends BaseService<Database, Long> implements DatabaseService {
  public DatabaseServiceImpl(DatabaseRepository repository) {
    super(repository, (entity) -> null);
  }

  @Override
  public List<DatabaseTable> getTables(Database database) {
    return query(database, (connection) -> {
      ResultSet rs = connection.getMetaData().getTables(database.getCatalog(), database.getSchema(), null, new String[]{"TABLE"});
      List<DatabaseTable> result = new ArrayList<>();
      while (rs.next()) {
        result.add(SimpleDatabaseTable.builder()
          .name(rs.getString("TABLE_NAME"))
          .remarks(rs.getString("REMARKS"))
          .build());
      }
      return result;
    });
  }

  @Override
  public SimpleDatabaseTable getTable(Database database, String table) {
    return query(database, (connection -> {
      ResultSet rs = connection.getMetaData().getTables(database.getCatalog(), database.getSchema(), table, new String[]{"TABLE"});
      if (!rs.next()) {
        throw new IllegalStateException("表不存在");
      }
      return SimpleDatabaseTable.builder()
        .name(rs.getString("TABLE_NAME"))
        .remarks(rs.getString("REMARKS"))
        .columns(getColumns(database, table))
        .build();
    }));
  }

  protected List<SimpleDatabaseTableColumn> getColumns(Database database, String table) {
    return query(database, (connection -> {
      ResultSet rs = connection.getMetaData().getColumns(database.getCatalog(), database.getSchema(), table, null);
      List<SimpleDatabaseTableColumn> result = new ArrayList<>();
      List<String> primaryKeyNames = getPrimaryKeyNames(database, table);
      while (rs.next()) {
        String name = rs.getString("COLUMN_NAME");
        result.add(SimpleDatabaseTableColumn.builder()
          .name(name)
          .type(rs.getInt("DATA_TYPE"))
          .size(rs.getInt("COLUMN_SIZE"))
          .decimalDigits(rs.getInt("DECIMAL_DIGITS"))
          .remarks(rs.getString("REMARKS"))
          .isNullable("YES".equals(rs.getString("IS_NULLABLE")))
          .isAutoincrement("YES".equals(rs.getString("IS_AUTOINCREMENT")))
          .isPrimaryKey(primaryKeyNames.contains(name))
          .build());
      }
      return result;
    }));
  }

  public List<String> getPrimaryKeyNames(Database database, String table) {
    return query(database, (connection -> {
      ResultSet rs = connection.getMetaData().getPrimaryKeys(database.getCatalog(), database.getSchema(), table);
      List<String> result = new ArrayList<>();
      while (rs.next()) {
        result.add(rs.getString("COLUMN_NAME"));
      }
      return result;
    }));
  }

  protected <R> R query(Database database, ConnectionQuery<R> query) {
    try (Connection connection = getConnection(database)) {
      return query.query(connection);
    } catch (SQLException e) {
      LOGGER.error("数据库[id=%s]操作异常".formatted(database.getId()), e);
      throw new IllegalStateException("数据库操作异常");
    }
  }

  protected Connection getConnection(Database database) throws SQLException {
    return DriverManager.getConnection(database.getUrl(), database.getUsername(), database.getPassword());
  }
}