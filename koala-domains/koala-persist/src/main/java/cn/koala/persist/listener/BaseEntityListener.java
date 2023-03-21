package cn.koala.persist.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 基础实体监听器抽象类
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public abstract class BaseEntityListener implements EntityListener, EntityListenerSelector {
  protected final Class<?> entityType;

  @Override
  public boolean match(Class<?> entityType) {
    if (this.entityType == null || entityType == null) {
      return false;
    }
    return this.entityType.isAssignableFrom(entityType);
  }
}
