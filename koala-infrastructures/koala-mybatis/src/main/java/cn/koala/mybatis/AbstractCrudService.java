package cn.koala.mybatis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 增删改查抽象类
 *
 * @param <T> 主键类型
 * @param <E> 实体类型
 * @author Houtaroy
 */
public abstract class AbstractCrudService<T, E> implements CrudService<T, E> {

  /**
   * 获取分页存储库对象
   *
   * @return 分页存储库对象
   */
  public abstract PageRepository<T, E> getRepository();

  @Override
  public Page<E> list(Map<String, Object> parameters, Pageable pageable) {
    return PageEnhancedHelper.page(() -> getRepository().findAll(parameters, pageable), pageable);
  }

  @Override
  public List<E> list(Map<String, Object> parameters) {
    return getRepository().findAll(parameters, null);
  }

  @Override
  public Optional<E> load(T id) {
    return getRepository().findById(id);
  }

  @Override
  public void add(E entity) {
    getRepository().add(entity);
  }

  @Override
  public void update(E entity) {
    getRepository().update(entity);
  }

  @Override
  public void delete(E entity) {
    getRepository().delete(entity);
  }
}
