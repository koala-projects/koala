package cn.koala.persist;

import jakarta.persistence.PrePersist;

/**
 * 实体监听器接口
 * <p>
 * 仅提供判断是否支持指定实体的方法, 监听行为请使用{@link PrePersist}等相关注解标注
 *
 * @author Houtaroy
 */
public interface EntityListener {
  default boolean support(Object entity) {
    return false;
  }
}
