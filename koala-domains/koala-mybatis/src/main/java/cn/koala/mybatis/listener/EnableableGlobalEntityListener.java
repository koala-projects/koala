package cn.koala.mybatis.listener;

import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.YesNo;
import jakarta.persistence.PrePersist;

/**
 * 可启用的实体监听器
 *
 * @author Houtaroy
 */
public class EnableableGlobalEntityListener extends AbstractGlobalEntityListener<Enableable> {

  @PrePersist
  public void prePersist(Enableable entity) {
    if (entity.getEnabled() == null) {
      entity.setEnabled(YesNo.YES);
    }
  }
}
