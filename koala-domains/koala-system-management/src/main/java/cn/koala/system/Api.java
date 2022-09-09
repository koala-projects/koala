package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Sortable;
import cn.koala.persistence.Stateable;

/**
 * @author Houtaroy
 */
public interface Api extends Idable<String>, Codeable, Sortable, Stateable, Auditable {

  /**
   * 获取接口地址
   *
   * @return 接口地址
   */
  String getUrl();

  /**
   * 获取请求方式
   *
   * @return 请求方式
   */
  String getMethod();
}
