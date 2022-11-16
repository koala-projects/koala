package cn.koala.data;

import cn.koala.persistence.Deletable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Systemic;

/**
 * 数据源接口
 *
 * @author Houtaroy
 */
public interface DataSource extends Idable<String>, Systemic, Deletable {
  /**
   * 获取数据源名称
   *
   * @return 数据源名称
   */
  String getName();

  /**
   * 获取数据库连接
   *
   * @return 数据库连接
   */
  String getUrl();

  /**
   * 获取用户名
   *
   * @return 用户名
   */
  String getUsername();

  /**
   * 获取密码
   *
   * @return 密码
   */
  String getPassword();
}
