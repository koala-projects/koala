package cn.houtaroy.koala.component.druid;

import com.alibaba.druid.DbType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NoSuchWallProviderException extends Exception {

  private final DbType dbType;

  /**
   * 构造函数
   *
   * @param dbType 数据库类型
   */
  public NoSuchWallProviderException(DbType dbType) {
    super(String.format("未找到数据库[%s]对应的WallProvider, 可自行添加", dbType.name()));
    this.dbType = dbType;
  }
}
