package cn.koala.system.mybatis;

import cn.koala.mybatis.AbstractCrudService;
import cn.koala.persistence.Idable;
import cn.koala.web.NoSuchDataException;
import cn.koala.web.PropertyHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.beans.FeatureDescriptor;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @param <T> 主键类型
 * @param <E> 实体类型
 * @author Houtaroy
 */
@Slf4j
public abstract class AbstractSystemCrudService<T, E> extends AbstractCrudService<T, E> {

  @Override
  public void add(E entity) {
    auditAdd(entity);
    super.add(entity);
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
    super.update(entity);
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
    super.delete(entity);
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
