package cn.koala.database.service;

import cn.koala.database.domain.Database;
import cn.koala.database.domain.DatabaseConnectionQuery;
import cn.koala.database.domain.DatabaseTable;
import cn.koala.database.domain.SimpleDatabaseTable;
import cn.koala.database.domain.SimpleDatabaseTableColumn;
import cn.koala.database.repository.DatabaseRepository;
import cn.koala.exception.BusinessException;
import cn.koala.mybatis.service.AbstractSmartService;
import cn.koala.util.Assert;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Getter
public class DefaultDatabaseService extends AbstractSmartService<Long, Database, Long> implements DatabaseService {

  public static final int TIME_OUT = 60;

  private final DatabaseRepository repository;

  @Override
  public List<DatabaseTable> listTable(Long id) {
    Database database = load(id);
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
  public List<DatabaseTable> listTable(Long id, List<String> names) {
    Assert.notEmpty(names, "表名不能为空");
    Database database = repository.load(id).orElseThrow(() -> new BusinessException("数据库不存在"));
    return names.stream().map(name -> loadTable(database, name)).toList();
  }

  @Override
  public DatabaseTable loadTable(Long id, String name) {
    Assert.hasLength(name, "表名不能为空");
    Database database = repository.load(id).orElseThrow(() -> new BusinessException("数据库不存在"));
    return loadTable(database, name);
  }

  protected DatabaseTable loadTable(Database database, String table) {
    return query(database, (connection -> {
      ResultSet rs = connection.getMetaData().getTables(database.getCatalog(), database.getSchema(), table, new String[]{"TABLE"});
      if (!rs.next()) {
        throw new BusinessException("表不存在");
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

  protected List<String> getPrimaryKeyNames(Database database, String table) {
    return query(database, (connection -> {
      ResultSet rs = connection.getMetaData().getPrimaryKeys(database.getCatalog(), database.getSchema(), table);
      List<String> result = new ArrayList<>();
      while (rs.next()) {
        result.add(rs.getString("COLUMN_NAME"));
      }
      return result;
    }));
  }

  @Override
  public boolean isConnectable(Database database) {
    try (Connection connection = getConnection(database)) {
      return connection.isValid(TIME_OUT);
    } catch (SQLException e) {
      return false;
    }
  }

  protected <R> R query(Database database, DatabaseConnectionQuery<R> query) {
    try (Connection connection = getConnection(database)) {
      return query.query(connection);
    } catch (SQLException e) {
      throw new BusinessException("数据库操作异常", e);
    }
  }

  protected Connection getConnection(Database database) throws SQLException {
    return DriverManager.getConnection(database.getUrl(), database.getUsername(), database.getPassword());
  }
}
