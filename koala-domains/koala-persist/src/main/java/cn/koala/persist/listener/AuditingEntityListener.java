package cn.koala.persist.listener;

import cn.koala.persist.domain.Auditable;
import cn.koala.toolkit.DateHelper;
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
@Order(2000)
@SuppressWarnings({"unchecked", "rawtypes"})
public class AuditingEntityListener extends AbstractEntityListener<Auditable> {
  protected final ObjectProvider<AuditorAware<?>> auditorAware;

  public AuditingEntityListener(@NonNull ObjectProvider<AuditorAware<?>> auditorAware) {
    Assert.notNull(auditorAware, "AuditorAware不能为空");
    this.auditorAware = auditorAware;
  }

  @Override
  public void preAdd(Auditable entity) {
    Auditable<Object> auditable = ((Auditable<Object>) entity);
    auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setCreatedBy));
    auditable.setCreatedTime(DateHelper.now());
  }

  @Override
  public void preUpdate(Auditable entity, Auditable persist) {
    Auditable<Object> auditable = ((Auditable<Object>) entity);
    auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setLastModifiedBy));
    auditable.setLastModifiedTime(DateHelper.now());
  }

  @Override
  public void preDelete(Auditable entity, Auditable persist) {
    Auditable<Object> auditable = ((Auditable<Object>) entity);
    auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setDeletedBy));
    auditable.setDeletedTime(DateHelper.now());
  }
}
