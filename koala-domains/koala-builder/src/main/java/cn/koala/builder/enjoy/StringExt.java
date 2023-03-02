package cn.koala.builder.enjoy;

import cn.koala.toolkit.word.WordHelper;
import com.google.common.base.CaseFormat;

/**
 * Enjoy模板引擎字符串拓展类
 *
 * @author Houtaroy
 */
public class StringExt {
  public String plural(String self) {
    return WordHelper.plural(self);
  }

  public String format(String self, String current, String forward) {
    return CaseFormat.valueOf(current).to(CaseFormat.valueOf(forward), self);
  }
}
