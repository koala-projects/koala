package cn.houtaroy.koala.infrastructure.services;

import cn.houtaroy.koala.infrastructure.repositories.CrudRepository;
import cn.koala.system.entities.BaseEntity;
import cn.koala.system.exceptions.NoSuchDataException;
import cn.koala.system.models.Idable;
import cn.koala.system.models.User;
import cn.koala.system.services.CrudService;
import cn.koala.system.utils.PropertyUtil;
import cn.koala.system.utils.SpringSecurityUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.beans.FeatureDescriptor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @param <T> 主键类型
 * @param <E> 实体类型
 * @author Houtaroy
 */
@Slf4j
public abstract class BaseCrudService<T, E> implements CrudService<T, E> {

  /**
   * 获取存储库类
   *
   * @return 存储库类
   */
  public abstract CrudRepository<T, E> getRepository();

  @Override
  public Page<E> list(Map<String, Object> parameters, Pageable pageable) {
    Assert.notNull(pageable, "分页参数不能为空");
    int pageNumber = pageable.getPageNumber();
    PageHelper.startPage(pageNumber == 0 ? 1 : pageNumber, pageable.getPageSize());
    com.github.pagehelper.Page<E> result =
      (com.github.pagehelper.Page<E>) getRepository().findAll(parameters, pageable);
    return new PageImpl<>(result, pageable, result.getTotal());
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
    auditAdd(entity);
    getRepository().add(entity);
  }

  @Override
  public void update(E entity) {
    if (entity instanceof Idable<?>) {
      Idable<T> idable = (Idable<T>) entity;
      Optional<E> optionalDatabaseObject = getRepository().findById(idable.getId());
      if (optionalDatabaseObject.isEmpty()) {
        LOGGER.error("数据[id={}]不存在", idable.getId());
        throw new NoSuchDataException("数据不存在");
      }
      E databaseObject = optionalDatabaseObject.get();
      BeanUtils.copyProperties(entity, databaseObject,
        PropertyUtil.getNullProperty(entity).stream().map(FeatureDescriptor::getName).toArray(String[]::new));
      entity = databaseObject;
    }
    auditUpdate(entity);
    getRepository().update(entity);
  }

  @Override
  public void delete(E entity) {
    auditDelete(entity);
    getRepository().delete(entity);
  }

  protected void auditAdd(E entity) {
    if (entity instanceof BaseEntity base) {
      audit(base::setCreateTime, base::setCreateUser);
    }
  }

  protected void auditUpdate(E entity) {
    if (entity instanceof BaseEntity base) {
      audit(base::setLastModifyTime, base::setLastModifyUser);
    }
  }

  protected void auditDelete(E entity) {
    if (entity instanceof BaseEntity base) {
      audit(base::setDeleteTime, base::setDeleteUser);
    }
  }

  protected void audit(Consumer<LocalDateTime> timeConsumer, Consumer<User> userConsumer) {
    timeConsumer.accept(LocalDateTime.now());
    SpringSecurityUtil.currentUser().ifPresent(userConsumer);
  }
}
