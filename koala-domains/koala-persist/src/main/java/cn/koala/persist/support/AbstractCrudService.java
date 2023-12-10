package cn.koala.persist.support;

import cn.koala.persist.CrudRepository;
import cn.koala.persist.CrudService;
import lombok.NonNull;
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
@Deprecated
public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> {

  @Override
  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    List<T> entities = list(parameters);
    return new PageImpl<>(doPage(entities, pageable), pageable, entities.size());
  }

  /**
   * 物理分页
   *
   * @param entities 所有实体列表
   * @param pageable 分页对象
   * @return 分页后的实体列表结果
   */
  protected List<T> doPage(List<T> entities, Pageable pageable) {
    int pageNumber = Math.max(pageable.getPageNumber() + 1, 1);
    int startIndex = (pageNumber - 1) * pageable.getPageSize();
    int endIndex = Math.min(startIndex + pageable.getPageSize() - 1, entities.size());
    return entities.subList(startIndex, endIndex);
  }

  @Override
  public List<T> list(Map<String, Object> parameters) {
    return getRepository().list(parameters);
  }

  @Override
  public T load(ID id) {
    return getRepository().load(id).orElse(null);
  }

  @Override
  public <S extends T> void create(@NonNull S entity) {
    getRepository().create(entity);
  }

  @Override
  public <S extends T> void update(@NonNull S entity) {
    getRepository().update(entity);
  }

  @Override
  public <S extends T> void delete(@NonNull S entity) {
    getRepository().delete(entity);
  }

  protected abstract CrudRepository<T, ID> getRepository();
}
