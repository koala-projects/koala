package cn.koala.persist.support;

import cn.koala.persist.CrudServiceManager;
import cn.koala.persist.domain.Persistable;
import cn.koala.persist.domain.Stateful;
import cn.koala.persist.domain.YesNo;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.util.Optional;

/**
 * 状态实体监听器
 *
 * @author Houtaroy
 */
@Order(1000)
@RequiredArgsConstructor
public class StatefulEntityListener extends AbstractInheritedEntityListener<Stateful> {

  private final CrudServiceManager manager;

  @PrePersist
  public void preAdd(Stateful entity) {
    entity.setIsEnabled(YesNo.YES);
    entity.setIsSystemic(YesNo.NO);
    entity.setIsDeleted(YesNo.NO);
  }

  @PreUpdate
  public void preUpdate(Stateful entity) {
    DomainHelper.checkEditable(load(entity));
    entity.setIsSystemic(YesNo.NO);
    entity.setIsDeleted(YesNo.NO);
  }

  @PreRemove
  public void preDelete(Stateful entity) {
    DomainHelper.checkEditable(load(entity));
    entity.setIsDeleted(YesNo.YES);
  }

  protected Stateful load(Stateful entity) {
    Class<? extends Stateful> entityClass = entity.getClass();
    if (entity instanceof Persistable<?> persistable) {
      Object id = persistable.getId();
      return load(id, entityClass, id.getClass()).orElse(null);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  protected <T, ID> Optional<T> load(Object id, Class<T> entityClass, Class<ID> idClass) {
    return Optional.ofNullable(manager.getService(entityClass, idClass))
      .map(service -> service.load((ID) id));
  }
}
