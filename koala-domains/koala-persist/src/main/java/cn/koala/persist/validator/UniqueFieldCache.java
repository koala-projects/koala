package cn.koala.persist.validator;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 非重复字段缓存器
 *
 * @author Houtaroy
 */
public class UniqueFieldCache {

  private final static String MESSAGE_TEMPLATE_SUFFIX = "[%s]{system.unique}";
  private final MultiValueMap<Class<?>, Field> fields = new LinkedMultiValueMap<>();
  private final Map<Field, String> messageTemplates = new HashMap<>();

  protected List<Field> getFields(Class<?> entityClass, ReflectionUtils.FieldFilter filter) {
    if (!fields.containsKey(entityClass)) {
      ReflectionUtils.doWithFields(entityClass, field -> addFiled(entityClass, field), filter);
    }
    return fields.get(entityClass);
  }

  protected void addFiled(Class<?> entityClass, Field field) {
    field.setAccessible(true);
    fields.add(entityClass, field);
  }

  protected String getMessageTemplate(Field field) {
    return messageTemplates.computeIfAbsent(field, data -> {
      Schema schema = field.getAnnotation(Schema.class);
      return (schema == null ? "值" : schema.description()) + MESSAGE_TEMPLATE_SUFFIX;
    });
  }
}
