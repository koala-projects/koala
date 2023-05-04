package cn.koala.persist.listener;

/**
 * 实体监听器
 *
 * @author Houtaroy
 */
@Deprecated
public interface EntityListener<T> {
  Class<T> getEntityClass();

  default <S extends T> void preAdd(S entity) {

  }

  default <S extends T> void postAdd(S entity) {
  }

  default <S extends T> void preUpdate(S entity, S persist) {
  }

  default <S extends T> void postUpdate(S entity) {
  }

  default <S extends T> void preDelete(S entity, S persist) {
  }

  default <S extends T> void postDelete(S entity) {
  }
}
