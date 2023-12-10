package cn.koala.persist.support;

import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.core.annotation.Order;

/**
 * 状态实体监听器
 *
 * @author Houtaroy
 */
@Deprecated
@Order(1000)
public class StatefulEntityListener extends AbstractSystemEntityListener<Stateful> {

  @PrePersist
  public void preAdd(Stateful entity) {
    entity.setIsEnabledIfAbsent(YesNo.YES);
    entity.setIsSystemic(YesNo.NO);
    entity.setIsDeleted(YesNo.NO);
  }

  @PreUpdate
  public void preUpdate(Stateful entity) {
    entity.setIsSystemic(null);
    entity.setIsDeleted(null);
  }

  @PreRemove
  public void preDelete(Stateful entity) {
    entity.setIsDeleted(YesNo.YES);
  }
}
