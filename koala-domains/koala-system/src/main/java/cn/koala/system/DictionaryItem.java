package cn.koala.system;

import cn.koala.persistence.Deletable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Systemic;

/**
 * 字典项
 *
 * @author Houtaroy
 */
public interface DictionaryItem extends Idable<String>, Systemic, Deletable {
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
