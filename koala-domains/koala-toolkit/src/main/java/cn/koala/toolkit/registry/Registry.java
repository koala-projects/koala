package cn.koala.toolkit.registry;

import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

/**
 * 注册表接口
 *
 * @author Houtaroy
 */
@Deprecated
public interface Registry<K, V> {
  void register(V value);

  void unregister(V value);

  Optional<V> get(K key);

  @NonNull
  List<V> getAll(K key);

  default boolean contains(K key) {
    return get(key).isPresent();
  }
}
