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
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

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
  private String message;
  private CrudServiceManager manager;

  @Override
  public boolean isValid(Persistable<?> value, ConstraintValidatorContext context) {
    try {
      Field field = CACHE.getField(value.getClass(), name);
      Object fieldValue = field.get(value);
      if (isDuplicate(value, name, fieldValue)) {
        buildErrorMessage(context, message, field, fieldValue);
        return false;
      }
      return true;
    } catch (Exception e) {
      LOGGER.error("校验字段是否重复失败", e);
      return false;
    }
  }

  protected boolean isDuplicate(Persistable<?> entity, String fieldName, Object fieldValue) {
    CrudService<?, ?> service = manager.getService(entity.getClass(), entity.getId().getClass());
    return service.list(Map.of(fieldName, fieldValue))
      .stream()
      .filter(data -> data instanceof Persistable<?>)
      .map(Persistable.class::cast)
      .anyMatch(persistable -> !Objects.equals(entity.getId(), persistable.getId()));
  }

  protected void buildErrorMessage(ConstraintValidatorContext context, String message, Field field, Object fieldValue) {
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
    this.message = uniqueField.message();
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.manager = context.getBean(CrudServiceManager.class);
  }
}
