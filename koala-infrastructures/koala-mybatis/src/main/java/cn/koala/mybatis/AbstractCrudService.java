package cn.koala.mybatis;

import cn.koala.enhancement.YesNo;
import cn.koala.persistence.CrudService;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Stateable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

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
public abstract class AbstractCrudService<T, E extends Idable<T>> implements CrudService<T, E> {
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
    Assert.isTrue(isNoSystem(entity), "权限不足, 请联系管理员");
    getRepository().update(entity);
  }

  @Override
  public void delete(E entity) {
    Assert.isTrue(isNoSystem(entity), "权限不足, 请联系管理员");
    getRepository().delete(entity);
  }

  /**
   * 是否非系统数据
   *
   * @param entity 数据实体
   * @return 是否非系统数据
   */
  protected boolean isNoSystem(E entity) {
    if (entity instanceof Stateable) {
      return getRepository().findById(entity.getId())
        .map(Stateable.class::cast)
        .map(stateable -> stateable.getIsSystem() == YesNo.NO)
        .orElse(false);
    }
    return true;
  }
}
