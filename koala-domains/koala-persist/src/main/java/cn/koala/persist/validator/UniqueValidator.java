package cn.koala.persist.validator;

import cn.koala.persist.CrudService;
import cn.koala.persist.CrudServiceManager;
import cn.koala.persist.domain.Persistable;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 可编辑数据ID校验器
 *
 * @author Houtaroy
 */
@Slf4j
@Component
public class UniqueValidator implements ConstraintValidator<Unique, Persistable<?>>, ApplicationContextAware {

  private final static UniqueFieldCache CACHE = new UniqueFieldCache();
  private ApplicationContext context;
  private CrudServiceManager manager;
  private List<String> fieldNames;

  @Override
  public boolean isValid(Persistable<?> value, ConstraintValidatorContext context) {
    try {
      List<UniqueError> errors = new ArrayList<>(fieldNames.size());
      for (Field field : CACHE.getFields(value.getClass(), field -> fieldNames.contains(field.getName()))) {
        Object fieldValue = field.get(value);
        if (isDuplicate(value, field.getName(), fieldValue)) {
          errors.add(new UniqueError(field, fieldValue));
        }
      }
      buildErrorMessages(context, errors);
      return errors.isEmpty();
    } catch (Exception e) {
      LOGGER.error("校验字段是否重复失败", e);
      return false;
    }
  }

  private boolean isDuplicate(Persistable<?> entity, String fieldName, Object fieldValue) {
    CrudService<?, ?> service = manager.getService(entity.getClass(), entity.getId().getClass());
    return service.list(Map.of(fieldName, fieldValue))
      .stream()
      .filter(data -> data instanceof Persistable<?>)
      .map(Persistable.class::cast)
      .anyMatch(persistable -> !Objects.equals(entity.getId(), persistable.getId()));
  }

  private void buildErrorMessages(ConstraintValidatorContext context, List<UniqueError> errors) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(
      errors.stream()
        .map(error -> CACHE.getMessageTemplate(error.getField()).formatted(error.getValue()))
        .collect(Collectors.joining(", "))
    ).addConstraintViolation();
  }

  @Override
  public void initialize(Unique unique) {
    this.manager = context.getBean(CrudServiceManager.class);
    this.fieldNames = Arrays.stream(unique.fields()).toList();
  }

  @Override
  public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
    this.context = context;
  }

  @Data
  @RequiredArgsConstructor
  private static class UniqueError {
    private final Field field;
    private final Object value;
  }
}
