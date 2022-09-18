package cn.koala.template;

import org.apache.commons.lang3.StringUtils;

/**
 * Enjoy字符串拓展
 *
 * @author Houtaroy
 */
public class EnjoyStringExt {
  /**
   * 首字母大写
   *
   * @param self 字符串自身
   * @return 首大写后的字符串
   */
  public String capitalize(String self) {
    return StringUtils.capitalize(self);
  }
}
