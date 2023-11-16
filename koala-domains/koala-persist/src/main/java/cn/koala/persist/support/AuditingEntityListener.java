package cn.koala.persist.support;

import cn.koala.persist.domain.Auditable;
import cn.koala.util.LocalDateTimeUtils;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.data.domain.AuditorAware;

/**
 * 审计实体监听器
 * <p>
 * 基于{@link AuditorAware}实现审计功能
 *
 * @author Houtaroy
 */
public class AuditingEntityListener<T> extends AbstractSystemEntityListener<Auditable<T>> {

  protected final AuditorAware<T> auditorAware;

  public AuditingEntityListener(AuditorAware<T> auditorAware) {
    this.auditorAware = auditorAware;
  }

  @PrePersist
  public void prePersist(Auditable<T> entity) {
    if (auditorAware != null) {
      entity.setCreatedBy(auditorAware.getCurrentAuditor().orElse(null));
      entity.setCreatedTime(LocalDateTimeUtils.toDate());
    }

  }

  @PreUpdate
  public void preUpdate(Auditable<T> entity) {
    if (auditorAware != null) {
      entity.setLastModifiedBy(auditorAware.getCurrentAuditor().orElse(null));
      entity.setLastModifiedTime(LocalDateTimeUtils.toDate());
    }
  }

  @PreRemove
  public void preDelete(Auditable<T> entity) {
    if (auditorAware != null) {
      entity.setDeletedBy(auditorAware.getCurrentAuditor().orElse(null));
      entity.setDeletedTime(LocalDateTimeUtils.toDate());
    }
  }
}
