package cn.koala.persist.listener;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.AuditorAware;
import cn.koala.toolkit.DateHelper;
import lombok.NonNull;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

/**
 * 审计实体监听器
 *
 * @author Houtaroy
 */
@Order(2000)
public class AuditingEntityListener extends BaseEntityListener {
  protected final ObjectProvider<AuditorAware<?>> auditorAware;

  public AuditingEntityListener(@NonNull ObjectProvider<AuditorAware<?>> auditorAware) {
    super(Auditable.class);
    Assert.notNull(auditorAware, "AuditorAware不能为空");
    this.auditorAware = auditorAware;
  }

  @Override
  public void beforeAdd(Object entity) {
    if (auditorAware != null && entity instanceof Auditable<?>) {
      Auditable<Object> auditable = ((Auditable<Object>) entity);
      auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setCreatedBy));
      auditable.setCreatedTime(DateHelper.now());
    }
  }

  @Override
  public void afterAdd(Object entity) {

  }

  @Override
  public void beforeUpdate(Object entity, Object persist) {
    if (entity instanceof Auditable<?>) {
      Auditable<Object> auditable = ((Auditable<Object>) entity);
      auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setLastModifiedBy));
      auditable.setLastModifiedTime(DateHelper.now());
    }
  }

  @Override
  public void afterUpdate(Object entity) {

  }

  @Override
  public void beforeDelete(Object entity, Object persist) {
    if (entity instanceof Auditable<?>) {
      Auditable<Object> auditable = ((Auditable<Object>) entity);
      auditorAware.ifAvailable(aware -> aware.getCurrentAuditor().ifPresent(auditable::setDeletedBy));
      auditable.setDeletedTime(DateHelper.now());
    }
  }

  @Override
  public void afterDelete(Object entity) {

  }
}
