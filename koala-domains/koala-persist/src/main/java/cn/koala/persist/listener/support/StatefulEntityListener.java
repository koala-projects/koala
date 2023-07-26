package cn.koala.persist.listener.support;

import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

/**
 * 状态实体监听器
 *
 * @author Houtaroy
 */
@Order(6100)
@RequiredArgsConstructor
public class StatefulEntityListener extends AbstractInheritedEntityListener<Stateful> {

  @PrePersist
  public void preAdd(Stateful entity) {
    entity.setIsEnabled(YesNo.YES);
    entity.setIsSystemic(YesNo.NO);
    entity.setIsDeleted(YesNo.NO);
  }

  @PreUpdate
  public void preUpdate(Stateful entity) {
    entity.setIsSystemic(YesNo.NO);
    entity.setIsDeleted(YesNo.NO);
  }

  @PreRemove
  public void preDelete(Stateful entity) {
    entity.setIsDeleted(YesNo.YES);
  }
}
