package cn.koala.database.services;

import cn.koala.database.DatabaseHelper;
import cn.koala.database.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 基于DataSource的数据库服务实现类
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class DataSourceTableService implements TableService {
  protected final DataSource dataSource;

  @Override
  public List<Table> list(Map<String, Object> parameters) {
    return filterByName(doList(), parameters);
  }

  protected List<Table> doList() {
    try {
      return DatabaseHelper.getTables(dataSource, null, null, null);
    } catch (SQLException e) {
      LOGGER.error("查询数据库表失败", e);
      throw new IllegalStateException("查询数据库表失败");
    }
  }

  protected List<Table> filterByName(List<Table> tables, Map<String, Object> parameters) {
    return Optional.ofNullable(parameters.get("name"))
      .map(Object::toString)
      .filter(StringUtils::hasLength)
      .map(name -> tables.stream().filter(table -> table.getTableName().contains(name)).toList())
      .orElse(tables);
  }

  @Override
  public Table load(String name) {
    try {
      return DatabaseHelper.getTable(dataSource, null, null, name, true);
    } catch (SQLException e) {
      String message = "查询数据库表[name=%s]失败".formatted(name);
      LOGGER.error(message, e);
      throw new IllegalStateException(message);
    }
  }
}
