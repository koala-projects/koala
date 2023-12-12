package cn.koala.data.util;

import cn.koala.data.domain.Systemic;
import cn.koala.data.domain.YesNo;

/**
 * 领域工具类
 *
 * @author Houtaroy
 */
public abstract class DomainUtils {

  public static boolean isSystemic(Object object) {
    return object instanceof Systemic systemic && systemic.getSystemic() == YesNo.YES;
  }
}
