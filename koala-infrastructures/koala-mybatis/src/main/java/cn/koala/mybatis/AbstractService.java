package cn.koala.mybatis;

import cn.koala.persistence.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 抽象服务类, 基于MyBatis实现增删改查
 *
 * @param <K> 主键类型
 * @param <V> 实体类型
 * @author Houtaroy
 */
@Transactional
public abstract class AbstractService<K, V> implements CrudService<K, V> {

  @Override
  public Page<V> list(Map<String, Object> parameters, Pageable pageable) {
    return PageEnhancedHelper.page(() -> getRepository().findAll(parameters, pageable), pageable);
  }

  @Override
  public List<V> list(Map<String, Object> parameters) {
    return getRepository().findAll(parameters, null);
  }

  @Override
  public Optional<V> load(K id) {
    return getRepository().findById(id);
  }

  @Override
  public void add(V entity) {
    getRepository().add(entity);
  }

  @Override
  public void update(V entity) {
    getRepository().update(entity);
  }

  @Override
  public void delete(V entity) {
    getRepository().delete(entity);
  }

  /**
   * 获取分页存储库对象
   *
   * @return 分页存储库对象
   */
  protected abstract PageRepository<K, V> getRepository();
}
