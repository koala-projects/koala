package cn.koala.persist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 可持久化的模型
 *
 * @param <T> id类型
 * @author Houtaroy
 */
public interface Persistable<T> {
  T getId();

  void setId(T id);

  @JsonIgnore
  default void setIdIfAbsent(T id) {
    if (getId() == null) {
      setId(id);
    }
  }
}
