package cn.houtaroy.koala.starter.druid;

import cn.houtaroy.koala.component.druid.InMemorySqlService;
import cn.houtaroy.koala.component.druid.SqlService;
import com.alibaba.druid.DbType;
import com.alibaba.druid.wall.spi.DB2WallProvider;
import com.alibaba.druid.wall.spi.MySqlWallProvider;
import com.alibaba.druid.wall.spi.OracleWallProvider;
import com.alibaba.druid.wall.spi.PGWallProvider;
import com.alibaba.druid.wall.spi.SQLServerWallProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Houtaroy
 */
@Configuration
public class DruidAutoConfig {

  /**
   * SQL服务的bean
   *
   * @return SQL服务
   */
  @Bean
  @ConditionalOnMissingBean
  public SqlService sqlService() {
    InMemorySqlService result = new InMemorySqlService();
    result.addWallProvider(DbType.mysql, new MySqlWallProvider());
    result.addWallProvider(DbType.oracle, new OracleWallProvider());
    result.addWallProvider(DbType.sqlserver, new SQLServerWallProvider());
    result.addWallProvider(DbType.postgresql, new PGWallProvider());
    result.addWallProvider(DbType.db2, new DB2WallProvider());
    return new InMemorySqlService();
  }
}
