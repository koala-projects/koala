package cn.koala.persist.listener;

import cn.koala.persist.domain.Auditable;
import cn.koala.persist.domain.AuditorAware;
import cn.koala.toolkit.DateHelper;
import lombok.NonNull;
import org.springframework.util.Assert;

/**
 * 审计实体监听器
 *
 * @author Houtaroy
 */
public class AuditingEntityListener extends BaseEntityListener {
  protected final AuditorAware<?> auditorAware;

  public AuditingEntityListener(@NonNull AuditorAware<?> auditorAware) {
    super(Auditable.class);
    Assert.notNull(auditorAware, "AuditorAware不能为空");
    this.auditorAware = auditorAware;
  }

  @Override
  public void beforeAdd(Object entity) {
    if (auditorAware != null && entity instanceof Auditable<?>) {
      Auditable<Object> auditable = ((Auditable<Object>) entity);
      auditable.setCreatedBy(auditorAware.getCurrentAuditor().orElse(null));
      auditable.setCreatedTime(DateHelper.now());
    }
  }

  @Override
  public void afterAdd(Object entity) {

  }

  @Override
  public void beforeUpdate(Object entity) {
    if (entity instanceof Auditable<?>) {
      Auditable<Object> auditable = ((Auditable<Object>) entity);
      auditable.setLastModifiedBy(auditorAware.getCurrentAuditor().orElse(null));
      auditable.setLastModifiedTime(DateHelper.now());
    }
  }

  @Override
  public void afterUpdate(Object entity) {

  }

  @Override
  public void beforeDelete(Object entity) {
    if (entity instanceof Auditable<?>) {
      Auditable<Object> auditable = ((Auditable<Object>) entity);
      auditable.setDeletedBy(auditorAware.getCurrentAuditor().orElse(null));
      auditable.setDeletedTime(DateHelper.now());
    }
  }

  @Override
  public void afterDelete(Object entity) {

  }
}
