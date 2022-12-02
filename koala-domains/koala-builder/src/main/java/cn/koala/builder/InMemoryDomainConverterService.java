package cn.koala.builder;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 领域转换器服务, 内存实现
 *
 * @author Houtaroy
 */
public class InMemoryDomainConverterService implements DomainConverterService {
  protected final Map<String, DomainConverter> converters = new ConcurrentHashMap<>();

  /**
   * 构造函数
   *
   * @param converters 领域转换器列表
   */
  public InMemoryDomainConverterService(List<DomainConverter> converters) {
    converters.forEach(converter -> this.converters.put(converter.getId(), converter));
  }

  @Override
  public Page<DomainConverter> list(Map<String, Object> parameters, Pageable pageable) {
    List<DomainConverter> result = list(parameters);
    return new PageImpl<>(result, pageable, result.size());
  }

  @Override
  public List<DomainConverter> list(Map<String, Object> parameters) {
    return converters.values().stream().toList();
  }

  @Override
  public Optional<DomainConverter> load(String id) {
    return Optional.ofNullable(converters.get(id));
  }

  @Override
  public void add(DomainConverter entity) {
    converters.put(entity.getId(), entity);
  }

  @Override
  public void update(DomainConverter entity) {
    converters.put(entity.getId(), entity);
  }

  @Override
  public void delete(DomainConverter entity) {
    converters.remove(entity.getId());
  }
}
