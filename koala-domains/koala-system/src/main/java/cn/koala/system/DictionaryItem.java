package cn.koala.system;

import cn.koala.persistence.Idable;

/**
 * 字典项
 *
 * @author Houtaroy
 */
public interface DictionaryItem extends Idable<String> {
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
