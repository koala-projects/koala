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
    Optional<?> persist = load(id, entityClass, id.getClass());
    return persist.isPresent() && DomainHelper.isEditable(persist.get());
  }


  @SuppressWarnings("unchecked")
  protected <T, ID> Optional<T> load(Object id, Class<T> entityClass, Class<ID> idClass) {
    return Optional.ofNullable(manager.getService(entityClass, idClass))
      .map(service -> service.load((ID) id));
  }
}
