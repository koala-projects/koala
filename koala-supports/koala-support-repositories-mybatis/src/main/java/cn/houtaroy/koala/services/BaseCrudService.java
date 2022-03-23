package cn.houtaroy.koala.services;

import cn.houtaroy.koala.exceptions.NoSuchDataException;
import cn.houtaroy.koala.models.BaseEntity;
import cn.houtaroy.koala.models.Idable;
import cn.houtaroy.koala.models.User;
import cn.houtaroy.koala.repositories.CrudRepository;
import cn.houtaroy.koala.utils.PropertyUtil;
import cn.houtaroy.koala.utils.SpringSecurityUtil;
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
 * @param <IdType> 主键类型
 * @param <T>      实体类型
 * @author Houtaroy
 */
@Slf4j
public abstract class BaseCrudService<IdType, T> implements CrudService<IdType, T> {

  /**
   * 获取存储库类
   *
   * @return 存储库类
   */
  public abstract CrudRepository<IdType, T> getRepository();

  @Override
  public Page<T> list(Map<String, Object> parameters, Pageable pageable) {
    Assert.notNull(pageable, "分页参数不能为空");
    int pageNumber = pageable.getPageNumber();
    PageHelper.startPage(pageNumber == 0 ? 1 : pageNumber, pageable.getPageSize());
    com.github.pagehelper.Page<T> result =
      (com.github.pagehelper.Page<T>) getRepository().findAll(parameters, pageable);
    return new PageImpl<>(result, pageable, result.getTotal());
  }

  @Override
  public List<T> list(Map<String, Object> parameters) {
    return getRepository().findAll(parameters, null);
  }

  @Override
  public Optional<T> load(IdType id) {
    return getRepository().findById(id);
  }

  @Override
  public void add(T entity) {
    auditAdd(entity);
    getRepository().add(entity);
  }

  @Override
  public void update(T entity) {
    if (entity instanceof Idable<?>) {
      Idable<IdType> idable = (Idable<IdType>) entity;
      Optional<T> optionalDatabaseObject = getRepository().findById(idable.getId());
      if (optionalDatabaseObject.isEmpty()) {
        LOGGER.error("数据[id={}]不存在", idable.getId());
        throw new NoSuchDataException("数据不存在");
      }
      T databaseObject = optionalDatabaseObject.get();
      BeanUtils.copyProperties(entity, databaseObject,
        PropertyUtil.getNullProperty(entity).stream().map(FeatureDescriptor::getName).toArray(String[]::new));
      entity = databaseObject;
    }
    auditUpdate(entity);
    getRepository().update(entity);
  }

  @Override
  public void delete(T entity) {
    auditDelete(entity);
    getRepository().delete(entity);
  }

  protected void auditAdd(T entity) {
    if (entity instanceof BaseEntity base) {
      audit(base::setCreateTime, base::setCreateUser);
    }
  }

  protected void auditUpdate(T entity) {
    if (entity instanceof BaseEntity base) {
      audit(base::setLastModifyTime, base::setLastModifyUser);
    }
  }

  protected void auditDelete(T entity) {
    if (entity instanceof BaseEntity base) {
      audit(base::setDeleteTime, base::setDeleteUser);
    }
  }

  protected void audit(Consumer<LocalDateTime> timeConsumer, Consumer<User> userConsumer) {
    timeConsumer.accept(LocalDateTime.now());
    SpringSecurityUtil.currentUser().ifPresent(userConsumer);
  }
}
