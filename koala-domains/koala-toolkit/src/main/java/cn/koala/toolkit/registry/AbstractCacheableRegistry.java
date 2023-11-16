package cn.koala.toolkit.registry;

import org.springframework.lang.NonNull;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 可缓存的注册表抽象类
 * <p>
 * 使用线程安全的{@link ConcurrentReferenceHashMap}缓存键值关系
 *
 * @author Houtaroy
 */
@Deprecated
public abstract class AbstractCacheableRegistry<K, V> extends AbstractRegistry<K, V> {

  protected final MultiValueMap<K, V> cache;

  public AbstractCacheableRegistry() {
    this(new ArrayList<>());
  }

  public AbstractCacheableRegistry(List<V> values) {
    super(values);
    this.cache = new MultiValueMapAdapter<>(new ConcurrentReferenceHashMap<>(values.size()));
  }

  @Override
  public void register(V value) {
    super.register(value);
    this.cache.clear();
  }

  @Override
  public void unregister(V value) {
    super.unregister(value);
    this.cache.clear();
  }

  @Override
  @NonNull
  public List<V> getAll(K key) {
    return cache.computeIfAbsent(key, k -> super.getAll(key));
  }
}
