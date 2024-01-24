package cn.koala.mybatis.service;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.YesNo;
import cn.koala.data.service.CrudService;
import cn.koala.data.util.DomainNames;
import cn.koala.data.util.DomainUtils;
import cn.koala.exception.BusinessException;
import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.util.Assert;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增删改查服务抽象类
 * <p>
 * 基于{@link CrudRepository}实现
 *
 * @author Houtaroy
 */
@Slf4j
public abstract class AbstractSmartService<T, ID> implements CrudService<T, ID> {

  @Override
  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    var temp = new HashMap<>(parameters);
    temp.put(DomainNames.PAGEABLE, pageable);
    com.github.pagehelper.Page<T> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> list(temp));
    return new PageImpl<>(page.getResult(), pageable, page.getTotal());
  }

  @Override
  public List<T> list(Map<String, Object> parameters) {
    var temp = new HashMap<>(parameters);
    temp.put(DomainNames.DELETED, YesNo.NO.name());
    return getRepository().list(temp);
  }

  @Override
  public T load(ID id) {
    return getRepository().load(id)
      .filter(persistence -> !DomainUtils.isDeleted(persistence))
      .orElseThrow(() -> new BusinessException("数据不存在"));
  }

  @Override
  public <S extends T> void create(@NonNull S entity) {
    getRepository().create(entity);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <S extends T> void update(@NonNull S entity) {
    if (entity instanceof Persistable<?>) {
      T persistence = load(((Persistable<ID>) entity).getId());
      Assert.isTrue(!DomainUtils.isSystemic(persistence), "系统数据不允许修改");
    }
    getRepository().update(entity);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <S extends T> void delete(@NonNull S entity) {
    if (entity instanceof Persistable<?>) {
      T persistence = load(((Persistable<ID>) entity).getId());
      Assert.isTrue(!DomainUtils.isSystemic(persistence), "系统数据不允许删除");
    }
    if (entity instanceof Auditable<?, ?>) {
      getRepository().update(entity);
    } else {
      getRepository().delete(entity);
    }
  }

  protected abstract CrudRepository<T, ID> getRepository();
}
