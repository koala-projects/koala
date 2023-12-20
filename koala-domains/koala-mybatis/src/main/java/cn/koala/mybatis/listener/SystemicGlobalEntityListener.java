package cn.koala.mybatis.listener;

import cn.koala.data.domain.Systemic;
import cn.koala.data.domain.YesNo;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.core.Ordered;

/**
 * 状态实体监听器
 *
 * @author Houtaroy
 */
public class SystemicGlobalEntityListener extends AbstractGlobalEntityListener<Systemic> implements Ordered {

  @Override
  public int getOrder() {
    return GlobalEntityListenerOrders.SYSTEMIC;
  }

  @PrePersist
  public void prePersist(Systemic entity) {
    entity.setSystemic(YesNo.NO);
  }

  @PreUpdate
  public void preUpdate(Systemic entity) {
    if (entity.getSystemic() != null) {
      entity.setSystemic(null);
    }
  }
}
