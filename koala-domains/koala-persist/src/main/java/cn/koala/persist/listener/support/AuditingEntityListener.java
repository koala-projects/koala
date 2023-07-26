package cn.koala.persist.listener.support;

import cn.koala.persist.domain.Auditable;
import cn.koala.toolkit.DateHelper;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.NonNull;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.Assert;

/**
 * 审计实体监听器
 *
 * @author Houtaroy
 */
@Order(6200)
@SuppressWarnings({"unchecked", "rawtypes"})
public class AuditingEntityListener extends AbstractInheritedEntityListener<Auditable> {
  protected final ObjectProvider<AuditorAware<?>> auditorAware;

  public AuditingEntityListener(@NonNull ObjectProvider<AuditorAware<?>> auditorAware) {
    Assert.notNull(auditorAware, "AuditorAware不能为空");
    this.auditorAware = auditorAware;
  }

  @PrePersist
  public void prePersist(Auditable entity) {
    Auditable<Object> auditable = ((Auditable<Object>) entity);
    auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setCreatedBy));
    auditable.setCreatedTime(DateHelper.now());
  }

  @PreUpdate
  public void preUpdate(Auditable entity) {
    Auditable<Object> auditable = ((Auditable<Object>) entity);
    auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setLastModifiedBy));
    auditable.setLastModifiedTime(DateHelper.now());
  }

  @PreRemove
  public void preDelete(Auditable entity) {
    Auditable<Object> auditable = ((Auditable<Object>) entity);
    auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setDeletedBy));
    auditable.setDeletedTime(DateHelper.now());
  }
}
