package cn.koala.persist.validator;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceManager;
import cn.koala.persist.domain.Persistable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 唯一字段校验器
 *
 * @author Houtaroy
 */
@Slf4j
@Component
public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Persistable<?>>, ApplicationContextAware {

  private final static UniqueFieldCache CACHE = new UniqueFieldCache();
  private String name;
  private String parameter;
  private String message;
  private CrudServiceManager manager;

  @Override
  public boolean isValid(Persistable<?> value, ConstraintValidatorContext context) {
    try {
      Field field = CACHE.getField(value.getClass(), name);
      Object fieldValue = field.get(value);
      if (isDuplicate(value, fieldValue)) {
        buildErrorMessage(context, field, fieldValue);
        return false;
      }
      return true;
    } catch (Exception e) {
      LOGGER.error("校验字段是否重复失败", e);
      return false;
    }
  }

  protected boolean isDuplicate(Persistable<?> entity, Object fieldValue) {
    CrudService<?, ?> service = manager.getService(entity.getClass());
    Assert.notNull(service, "未找到实体类型[%s]对应服务类, 唯一校验失败".formatted(entity.getClass().getName()));
    return service.list(Map.of(parameter, fieldValue))
      .stream()
      .filter(data -> data instanceof Persistable)
      .map(Persistable.class::cast)
      .anyMatch(persistable -> !persistable.getId().equals(entity.getId()));
  }

  protected void buildErrorMessage(ConstraintValidatorContext context, Field field, Object fieldValue) {
    if (useDefaultMessage(message)) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(CACHE.getMessageTemplate(field).formatted(fieldValue))
        .addConstraintViolation();
    }
  }

  protected boolean useDefaultMessage(String message) {
    return !StringUtils.hasText(message) || UniqueField.DEFAULT_MESSAGE_SUFFIX.equals(message);
  }

  @Override
  public void initialize(UniqueField uniqueField) {
    this.name = uniqueField.value();
    this.parameter = StringUtils.hasText(uniqueField.parameter()) ? uniqueField.parameter() : this.name;
    this.message = uniqueField.message();
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.manager = context.getBean(CrudServiceManager.class);
  }
}
