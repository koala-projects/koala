package cn.koala.persist.listener;

/**
 * 监听器接口
 *
 * @author Houtaroy
 */
public interface Listener<T> {

  default <S extends T> void preAdd(S entity) {
  }

  default <S extends T> void postAdd(S entity) {
  }

  default <S extends T> void preUpdate(S entity) {
  }

  default <S extends T> void postUpdate(S entity) {
  }

  default <S extends T> void preDelete(S entity) {
  }

  default <S extends T> void postDelete(S entity) {
  }
}
