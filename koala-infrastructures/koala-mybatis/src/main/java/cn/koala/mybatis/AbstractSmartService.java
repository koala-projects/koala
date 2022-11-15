package cn.koala.mybatis;

import cn.koala.enhancement.YesNo;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Systemic;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * 抽象智能服务类, 基于抽象服务类增加了部分逻辑功能:
 * <p>
 * 1. 自动生成主键
 * <p>
 * 2. 系统数据不允许修改删除
 *
 * @param <K> 主键类型
 * @param <V> 数据实体类型
 * @author Houtaroy
 */
public abstract class AbstractSmartService<K, V extends Idable<K>> extends AbstractService<K, V> {

  @Override
  public void add(V entity) {
    entity.setIdIfAbsent(getIdGenerator().apply(entity));
    super.add(entity);
  }

  @Override
  public void update(V entity) {
    Optional<V> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    super.update(entity);
  }

  @Override
  public void delete(V entity) {
    Optional<V> persist = getRepository().findById(entity.getId());
    Assert.isTrue(persist.isPresent(), "数据异常, 请联系管理员");
    Assert.isTrue(isNoSystem(persist.get()), "权限不足, 请联系管理员");
    super.delete(entity);
  }

  /**
   * 是否非系统数据
   *
   * @param entity 数据实体
   * @return 是否非系统数据
   */
  protected boolean isNoSystem(@NonNull V entity) {
    if (entity instanceof Systemic systemic) {
      return systemic.getIsSystem() == null || systemic.getIsSystem() == YesNo.NO;
    }
    return true;
  }

  /**
   * 获取ID生成器
   *
   * @return ID生成器
   */
  protected abstract IdGenerator<V, K> getIdGenerator();
}
