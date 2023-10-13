package cn.koala.persist.support;

import cn.koala.persist.domain.Auditable;
import cn.koala.toolkit.DateHelper;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.data.domain.AuditorAware;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public abstract class AbstractAuditingEntityListener<T> extends AbstractSystemEntityListener<Auditable<T>> {

  protected final AuditorAware<T> auditorAware;

  public AbstractAuditingEntityListener(AuditorAware<T> auditorAware) {
    this.auditorAware = auditorAware;
  }

  @PrePersist
  public void prePersist(Auditable<T> entity) {
    if (auditorAware != null) {
      entity.setCreatedBy(auditorAware.getCurrentAuditor().orElse(null));
      entity.setCreatedTime(DateHelper.now());
    }

  }

  @PreUpdate
  public void preUpdate(Auditable<T> entity) {
    if (auditorAware != null) {
      entity.setLastModifiedBy(auditorAware.getCurrentAuditor().orElse(null));
      entity.setLastModifiedTime(DateHelper.now());
    }
  }

  @PreRemove
  public void preDelete(Auditable<T> entity) {
    if (auditorAware != null) {
      entity.setDeletedBy(auditorAware.getCurrentAuditor().orElse(null));
      entity.setDeletedTime(DateHelper.now());
    }
  }
}
