package cn.koala.persist.initializer.support;

import cn.koala.persist.initializer.ScriptInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 脚本初始化器抽象类
 *
 * @author Houtaroy
 */
@Slf4j
public abstract class AbstractScriptInitializer implements ScriptInitializer {

  @Override
  public void init(DataSource dataSource) throws Exception {
    try (Connection connection = dataSource.getConnection()) {
      connection.setAutoCommit(false);
      getScripts().forEach(script -> doInit(connection, script));
      connection.commit();
    }
  }

  protected void doInit(Connection connection, String script) {
    Resource resource = new ClassPathResource(script);
    if (!resource.exists()) {
      LOGGER.warn("初始化器[{}]: 未找到脚本[{}]", getName(), script);
      return;
    }
    LOGGER.info("初始化器[{}]: 执行脚本[{}]", getName(), script);
    ScriptUtils.executeSqlScript(connection, resource);
  }
}
