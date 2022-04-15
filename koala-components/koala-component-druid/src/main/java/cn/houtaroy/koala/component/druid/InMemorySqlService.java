package cn.houtaroy.koala.component.druid;

import com.alibaba.druid.DbType;
import com.alibaba.druid.wall.WallCheckResult;
import com.alibaba.druid.wall.WallProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Houtaroy
 */
@SuppressWarnings("PMD")
public class InMemorySqlService implements SqlService {
  protected final Map<DbType, WallProvider> providers = new ConcurrentHashMap<>(DbType.values().length);

  /**
   * 新增防火墙
   *
   * @param dbType   数据库类型
   * @param provider 防火墙
   * @return 新增防火墙
   */
  public WallProvider addWallProvider(DbType dbType, WallProvider provider) {
    return providers.put(dbType, provider);
  }

  @Override
  public boolean isInjection(DbType dbType, String sql) throws NoSuchWallProviderException {
    return !wallCheck(dbType, sql).getViolations().isEmpty();
  }

  /**
   * 防火墙检查
   *
   * @param dbType 数据库类型
   * @param sql    sql语句
   * @return 检查结果
   * @throws NoSuchWallProviderException 没有找到防火墙提供者
   */
  public WallCheckResult wallCheck(DbType dbType, String sql) throws NoSuchWallProviderException {
    WallProvider provider = providers.get(dbType);
    if (provider == null) {
      throw new NoSuchWallProviderException(dbType);
    }
    return provider.check(sql);
  }
}
