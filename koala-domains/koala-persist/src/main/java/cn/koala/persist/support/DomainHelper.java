package cn.koala.persist.support;

import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * 领域帮助类
 *
 * @author Houtaroy
 */
public abstract class DomainHelper {

  public static void checkEditable(@Nullable Object domain) {
    Assert.notNull(domain, "数据不存在");
    Assert.state(isEditable(domain), "数据不允许修改");
  }

  public static boolean isEditable(@NonNull Object domain) {
    if (domain instanceof Stateful stateful) {
      return stateful.getIsSystemic() == YesNo.NO;
    }
    return true;
  }
}
