package cn.koala.persist.util;

import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;

/**
 * 领域帮助类
 *
 * @author Houtaroy
 */
public abstract class DomainUtils {

  public static boolean isSystemic(Object domain) {
    if (domain instanceof Stateful stateful) {
      return stateful.getIsSystemic() == YesNo.YES;
    }
    return false;
  }
}
