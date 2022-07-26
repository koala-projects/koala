package cn.koala.system.mybatis;

import cn.koala.mybatis.PageRepository;
import cn.koala.system.CrudService;
import cn.koala.system.Idable;
import cn.koala.web.NoSuchDataException;
import cn.koala.web.PropertyHelper;
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
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @param <T> 主键类型
 * @param <E> 实体类型
 * @author Houtaroy
 */
@Slf4j
public abstract class AbstractCrudService<T, E> implements CrudService<T, E> {
  /**
   * 获取存储库类
   *
   * @return 存储库类
   */
  public abstract PageRepository<T, E> getRepository();

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

  protected void auditAdd(E entity) {
    if (entity instanceof AbstractEntity base) {
      base.setIdIfNotProvided(UUID.randomUUID().toString());
      audit(base::setCreateTime, base::setCreateUser);
    }
  }

  @Override
  public void update(E entity) {
    if (entity instanceof Idable<?>) {
      Idable<T> idable = (Idable<T>) entity;
      E persistence = getPersistenceElseThrowException(idable.getId());
      BeanUtils.copyProperties(entity, persistence,
        PropertyHelper.getNullProperty(entity).stream().map(FeatureDescriptor::getName).toArray(String[]::new));
      entity = persistence;
    }
    auditUpdate(entity);
    getRepository().update(entity);
  }

  protected E getPersistenceElseThrowException(T id) {
    return getRepository().findById(id)
      .orElseThrow(() -> new NoSuchDataException(id));
  }

  protected void auditUpdate(E entity) {
    if (entity instanceof AbstractEntity base) {
      audit(base::setLastModifyTime, base::setLastModifyUser);
    }
  }

  @Override
  public void delete(E entity) {
    auditDelete(entity);
    getRepository().delete(entity);
  }

  protected void auditDelete(E entity) {
    if (entity instanceof AbstractEntity base) {
      audit(base::setDeleteTime, base::setDeleteUser);
    }
  }

  protected void audit(Consumer<LocalDateTime> timeConsumer, Consumer<UserEntity> userConsumer) {
    timeConsumer.accept(LocalDateTime.now());
    JwtUtil.currentUser().map(UserEntity.class::cast).ifPresent(userConsumer);
  }
}
