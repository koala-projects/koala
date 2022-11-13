package cn.koala.system;

import cn.koala.persistence.Idable;
import cn.koala.persistence.Stateable;

/**
 * 字典项
 *
 * @author Houtaroy
 */
public interface DictionaryItem extends Idable<String>, Stateable {
  /**
   * 获取字典项名称
   *
   * @return 字典项名称
   */
  String getName();

  /**
   * 获取字典项内容
   *
   * @return 字典项内容
   */
  String getContent();
}
