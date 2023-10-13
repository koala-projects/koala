package cn.koala.persist.support;

import org.springframework.core.annotation.Order;
import org.springframework.data.domain.AuditorAware;

/**
 * 默认审计实体监听器
 *
 * @author Houtaroy
 */
@Order(2000)
public class DefaultAuditingEntityListener extends AbstractAuditingEntityListener<Long> {

  public DefaultAuditingEntityListener(AuditorAware<Long> auditorAware) {
    super(auditorAware);
  }
}
