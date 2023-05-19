package cn.koala.toolkit.registry;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 注册表抽象类
 * <p>
 * 使用列表保存值, 子类通过实现方法{@link AbstractRegistry#matches(Object, Object) matches}进行键值匹配
 *
 * @author Houtaroy
 */
public abstract class AbstractRegistry<K, V> implements Registry<K, V> {

  protected final List<V> values;

  public AbstractRegistry() {
    this(new ArrayList<>());
  }

  public AbstractRegistry(List<V> values) {
    this.values = new ArrayList<>(values);
  }

  @Override
  public void register(V value) {
    this.values.add(value);
  }

  @Override
  public void unregister(V value) {
    this.values.remove(value);
  }

  @Override
  public Optional<V> get(K key) {
    return Optional.of(getAll(key))
      .filter(values -> !values.isEmpty())
      .map(values -> values.get(0));
  }

  @Override
  @NonNull
  public List<V> getAll(K key) {
    return values.stream()
      .filter(value -> this.matches(key, value))
      .toList();
  }

  protected abstract boolean matches(K key, V value);
}
