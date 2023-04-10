package cn.koala.persist;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 增删改查基础服务抽象类
 * <p>
 * 基于CrudRepository实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class BaseCrudService<T, ID> implements CrudService<T, ID> {
  protected final CrudRepository<T, ID> repository;

  @Override
  public List<T> list(Map<String, Object> parameters) {
    return repository.find(parameters);
  }

  @Override
  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    List<T> entities = list(parameters);
    return new PageImpl<>(doPage(entities, pageable), pageable, entities.size());
  }

  protected List<T> doPage(List<T> entities, Pageable pageable) {
    int pageNumber = Math.max(pageable.getPageNumber() + 1, 1);
    int startIndex = (pageNumber - 1) * pageable.getPageSize();
    int endIndex = Math.min(startIndex + pageable.getPageSize() - 1, entities.size());
    return entities.subList(startIndex, endIndex);
  }

  @Override
  public T load(ID id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public <S extends T> void add(@NonNull S entity) {
    repository.add(entity);
  }

  @Override
  public <S extends T> void update(@NonNull S entity) {
    repository.update(entity);
  }

  @Override
  public <S extends T> void delete(@NonNull S entity) {
    repository.delete(entity);
  }
}