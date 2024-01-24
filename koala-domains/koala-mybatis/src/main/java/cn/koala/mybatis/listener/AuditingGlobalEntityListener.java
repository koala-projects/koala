package cn.koala.mybatis.listener;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.YesNo;
import cn.koala.util.Assert;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.core.Ordered;
import org.springframework.data.domain.AuditorAware;

import java.util.Date;

/**
 * 审计全局实体监听器
 * <p>
 * 基于{@link AuditorAware}实现审计功能
 *
 * @author Houtaroy
 */
public class AuditingGlobalEntityListener<U> extends AbstractGlobalEntityListener<Auditable<U, ?>> implements Ordered {

  private final AuditorAware<U> auditorAware;

  public AuditingGlobalEntityListener(AuditorAware<U> auditorAware) {
    Assert.notNull(auditorAware, "AuditorAware不能为空");
    this.auditorAware = auditorAware;
  }

  @Override
  public int getOrder() {
    return GlobalEntityListenerOrders.AUDITING;
  }

  @PrePersist
  public void prePersist(Auditable<U, ?> entity) {
    entity.setDeleted(YesNo.NO);
    auditorAware.getCurrentAuditor().ifPresent(entity::setCreatedBy);
    entity.setCreatedDate(new Date());
  }

  @PreUpdate
  public void preUpdate(Auditable<U, ?> entity) {
    if (entity.getDeleted() != null) {
      entity.setDeleted(null);
    }
    auditorAware.getCurrentAuditor().ifPresent(entity::setLastModifiedBy);
    entity.setLastModifiedDate(new Date());
  }

  @PreRemove
  public void preDelete(Auditable<U, ?> entity) {
    entity.setDeleted(YesNo.YES);
    auditorAware.getCurrentAuditor().ifPresent(entity::setDeletedBy);
    entity.setDeletedDate(new Date());
  }
}
