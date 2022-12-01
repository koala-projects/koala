package cn.koala.builder;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 领域转换器服务, 内存实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class InMemoryDomainConverterService implements DomainConverterService {
  protected final Map<String, DomainConverter> converters = new ConcurrentHashMap<>();

  @Override
  public Optional<DomainConverter> load(String id) {
    return Optional.ofNullable(converters.get(id));
  }

  @Override
  public void add(DomainConverter entity) {
    converters.put(entity.getId(), entity);
  }

  @Override
  public void delete(String id) {
    converters.remove(id);
  }
}
