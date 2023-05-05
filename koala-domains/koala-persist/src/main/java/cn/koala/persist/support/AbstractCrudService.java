package cn.koala.persist.support;

import cn.koala.persist.CrudAction;
import cn.koala.persist.CrudRepository;
import cn.koala.persist.CrudService;
import cn.koala.persist.CrudType;
import cn.koala.persist.domain.Persistable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 增删改查基础服务抽象类
 * <p>
 * 基于CrudRepository实现
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> {

  protected final CrudRepository<T, ID> repository;

  @Override
  public List<T> list(Map<String, Object> parameters) {
    return repository.list(parameters);
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
    return repository.load(id).orElse(null);
  }

  @Override
  @CrudAction(CrudType.CREATE)
  public <S extends T> void create(@NonNull S entity) {
    repository.create(entity);
  }

  @Override
  @CrudAction(CrudType.UPDATE)
  public <S extends T> void update(@NonNull S entity) {
    preCheckBeforeUpdateAndDelete(entity);
    repository.update(entity);
  }

  @Override
  @CrudAction(CrudType.DELETE)
  public <S extends T> void delete(@NonNull S entity) {
    preCheckBeforeUpdateAndDelete(entity);
    repository.delete(entity);
  }

  @SuppressWarnings("unchecked")
  protected <S extends T> void preCheckBeforeUpdateAndDelete(S entity) {
    if (entity instanceof Persistable<?> persistable) {
      Optional<T> persist = repository.load(((Persistable<ID>) persistable).getId());
      Assert.isTrue(persist.isPresent(), "数据不存在");
      Assert.isTrue(!DomainHelper.isSystemic(persist.get()), "系统数据不允许修改");
    }
  }
}