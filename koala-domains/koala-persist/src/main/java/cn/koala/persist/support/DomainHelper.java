package cn.koala.persist.support;

import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;

import java.util.Optional;

/**
 * 领域帮助类
 *
 * @author Houtaroy
 */
public abstract class DomainHelper {

  public static boolean isSystemic(Object domain) {
    return Optional.ofNullable(domain)
      .filter(data -> data instanceof Stateful)
      .map(Stateful.class::cast)
      .map(data -> data.getIsSystemic() == YesNo.YES)
      .orElse(false);
  }
}
