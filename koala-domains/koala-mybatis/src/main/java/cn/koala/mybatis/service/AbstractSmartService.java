package cn.koala.mybatis.service;

import cn.koala.data.domain.Auditable;
import cn.koala.data.domain.Enableable;
import cn.koala.data.domain.Systemic;
import cn.koala.data.domain.YesNo;
import cn.koala.data.service.CrudService;
import cn.koala.data.util.DomainNames;
import cn.koala.data.util.DomainUtils;
import cn.koala.exception.BusinessException;
import cn.koala.mybatis.repository.CrudRepository;
import cn.koala.util.Assert;
import cn.koala.util.LocalDateTimeUtils;
import com.github.pagehelper.PageHelper;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 增删改查服务抽象类
 * <p>
 * 基于{@link CrudRepository}实现
 *
 * @author Houtaroy
 */
public abstract class AbstractSmartService<U, T, ID> implements CrudService<T, ID> {

  protected abstract CrudRepository<T, ID> getRepository();

  protected abstract AuditorAware<U> getAuditorAware();


  @Override

  public Page<T> page(Map<String, Object> parameters, Pageable pageable) {
    var temp = new HashMap<>(parameters);
    temp.put(DomainNames.PAGEABLE, pageable);
    temp.put(DomainNames.DELETED, YesNo.NO);
    com.github.pagehelper.Page<T> page = PageHelper
      .startPage(Math.max(pageable.getPageNumber() + 1, 1), pageable.getPageSize())
      .doSelectPage(() -> getRepository().list(temp));
    return new PageImpl<>(page.getResult(), pageable, page.getTotal());
  }

  @Override
  public List<T> list(Map<String, Object> parameters) {
    var temp = new HashMap<>(parameters);
    temp.put(DomainNames.DELETED, YesNo.NO);
    return getRepository().list(temp);
  }

  @Override
  public T load(ID id) {
    T result = getRepository().load(id).orElseThrow(() -> new BusinessException("数据不存在"));
    if (result instanceof Auditable<?, ?> auditable && auditable.getDeleted() == YesNo.YES) {
      throw new BusinessException("数据不存在");
    }
    return result;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <S extends T> void create(@NonNull S entity) {
    if (entity instanceof Enableable enableable && enableable.getEnabled() == null) {
      enableable.setEnabled(YesNo.YES);
    }
    if (entity instanceof Systemic systemic) {
      systemic.setSystemic(YesNo.NO);
    }
    if (entity instanceof Auditable<?, ?>) {
      Auditable<U, ID> auditable = (Auditable<U, ID>) entity;
      auditable.setDeleted(YesNo.NO);
      audit(auditable::setCreatedBy, auditable::setCreatedDate);
    }
    getRepository().create(entity);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <S extends T> void update(@NonNull S entity) {
    if (entity instanceof Persistable<?>) {
      T persistence = load(((Persistable<ID>) entity).getId());
      Assert.isTrue(!DomainUtils.isSystemic(persistence), "系统数据不允许删除");
    }
    if (entity instanceof Systemic systemic) {
      systemic.setSystemic(YesNo.NO);
    }
    if (entity instanceof Auditable<?, ?>) {
      Auditable<U, ID> auditable = (Auditable<U, ID>) entity;
      auditable.setDeleted(YesNo.NO);
      audit(auditable::setLastModifiedBy, auditable::setLastModifiedDate);
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
      Auditable<U, ID> auditable = (Auditable<U, ID>) entity;
      auditable.setDeleted(YesNo.YES);
      audit(auditable::setDeletedBy, auditable::setDeletedDate);
      getRepository().update(entity);
    } else {
      getRepository().delete(entity);
    }
  }

  protected void audit(Consumer<U> auditorConsumer, Consumer<Date> auditDateConsumer) {
    U auditor = getAuditorAware().getCurrentAuditor().orElseThrow(() -> new BusinessException("未找到审计用户"));
    auditorConsumer.accept(auditor);
    auditDateConsumer.accept(LocalDateTimeUtils.toDate());
  }
}
