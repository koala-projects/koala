package cn.koala.data.util;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Systemic;
import cn.koala.data.domain.YesNo;

/**
 * 领域工具类
 *
 * @author Houtaroy
 */
public abstract class DomainUtils {

  public static boolean isSystemic(Object domain) {
    return domain instanceof Systemic systemic && systemic.getSystemic() == YesNo.YES;
  }

  public static boolean isDeleted(Object domain) {
    return domain instanceof Auditable<?, ?> auditable && auditable.getDeleted() == YesNo.YES;
  }
}
