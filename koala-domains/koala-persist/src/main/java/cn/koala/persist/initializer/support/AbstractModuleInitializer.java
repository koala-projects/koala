package cn.koala.persist.initializer.support;

import cn.koala.persist.initializer.ModuleInitializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 模块初始化器抽象类
 * <p>
 * 默认实现了部分功能, 所有模块初始化器应继承此类
 * <p>
 * 脚本默认位置为: database/${模块名称}/schema-${数据库类型}.sql
 *
 * @author Houtaroy
 */
@Data
@Slf4j
public abstract class AbstractModuleInitializer implements ModuleInitializer {

  private static final String DEFAULT_SCHEMA = "schema";
  private static final String DEFAULT_DATA = "data";
  private static final String DEFAULT_SCRIPT_TEMPLATE = "database/%s/%s-%s.sql";

  @Override
  public String getSchema() {
    return DEFAULT_SCHEMA;
  }

  @Override
  public String getData() {
    return DEFAULT_DATA;
  }

  @Override
  public void init(DataSource dataSource) throws Exception {
    try (Connection connection = dataSource.getConnection()) {
      connection.setAutoCommit(false);
      String databaseProductName = getDatabaseProductName(connection);
      Resource schema = getScript(getSchema(), databaseProductName);
      if (schema.exists()) {
        LOGGER.info("模块[{}]初始化数据库结构...", getName());
        ScriptUtils.executeSqlScript(connection, schema);
      }
      Resource data = getScript(getData(), databaseProductName);
      if (data.exists()) {
        LOGGER.info("模块[{}]初始化数据...", getName());
        ScriptUtils.executeSqlScript(connection, data);
      }
      connection.commit();
    }
  }

  protected String getDatabaseProductName(Connection connection) throws SQLException {
    return connection.getMetaData().getDatabaseProductName().toLowerCase();
  }

  protected Resource getScript(String name, String databaseProductName) {
    return new ClassPathResource(DEFAULT_SCRIPT_TEMPLATE.formatted(getName(), name, databaseProductName));
  }
}
