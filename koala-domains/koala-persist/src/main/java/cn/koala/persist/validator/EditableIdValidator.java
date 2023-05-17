package cn.koala.persist.validator;

import cn.koala.persist.CrudServiceManager;
import cn.koala.persist.support.DomainHelper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 可编辑数据ID校验器
 *
 * @author Houtaroy
 */
@Component
public class EditableIdValidator implements ConstraintValidator<EditableId, Object>, ApplicationContextAware {

  private ApplicationContext context;
  private CrudServiceManager manager;
  private Class<?> entityClass;

  @Override
  public void initialize(EditableId editableId) {
    this.manager = context.getBean(CrudServiceManager.class);
    this.entityClass = editableId.value();
  }

  @Override
  public void setApplicationContext(@NonNull ApplicationContext context) throws BeansException {
    this.context = context;
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    return doValid(value);
  }

  protected boolean doValid(Object id) {
    return Optional.ofNullable(manager.getService(entityClass))
      .map(service -> service.load(id))
      .filter(DomainHelper::isEditable)
      .isPresent();
  }
}
