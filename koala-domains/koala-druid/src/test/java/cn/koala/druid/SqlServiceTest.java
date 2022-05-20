package cn.koala.druid;

import com.alibaba.druid.DbType;
import com.alibaba.druid.wall.spi.MySqlWallProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Houtaroy
 */
public class SqlServiceTest {

  /**
   * SQL编译测试
   */
  @Test
  void compile() {
    SqlService sqlService = new InMemorySqlService();
    String sql = "select * from t_user where id = #{id} and name like '#{name}%'";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("id", 1);
    parameters.put("name", "admin");
    Assertions.assertEquals(sqlService.compile(sql, parameters),
      "select * from t_user where id = 1 and name like 'admin%'");
  }

  /**
   * 检查SQL语句
   * 包含正确/错误/语法异常/存在SQL注入
   *
   * @throws NoSuchWallProviderException 无对应数据库的WallProvider
   */
  @Test
  void check() throws NoSuchWallProviderException {
    InMemorySqlService sqlService = new InMemorySqlService();
    sqlService.addWallProvider(DbType.mysql, new MySqlWallProvider());
    String success = "select * from t_user where id = 1";
    String injection = String.format("%s and 1 = 1", success);
    Assertions.assertFalse(sqlService.isInjection(DbType.mysql, success));
    Assertions.assertTrue(sqlService.isInjection(DbType.mysql, injection));
    Assertions.assertThrowsExactly(
      NoSuchWallProviderException.class,
      () -> sqlService.isInjection(DbType.dm, injection),
      "no such WallProvider for database dm, you can add one by yourself"
    );
  }
}
