package cn.koala.persist.validator;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 唯一字段缓存器
 *
 * @author Houtaroy
 */
public class UniqueFieldCache {
  private final static String DEFAULT_MESSAGE_PREFIX = "值";
  private final static String DEFAULT_MESSAGE_VALUE_PLACEHOLDER = "[%s]";
  private final Map<AbstractMap.SimpleEntry<Class<?>, String>, Field> fields = new HashMap<>();
  private final Map<Field, String> messageTemplates = new HashMap<>();

  public Field getField(Class<?> entityClass, String name) {
    AbstractMap.SimpleEntry<Class<?>, String> key = new AbstractMap.SimpleEntry<>(entityClass, name);
    if (!fields.containsKey(key)) {
      ReflectionUtils.doWithFields(
        entityClass,
        field -> addFiled(key, field),
        field -> field.getName().equals(name)
      );
    }
    return fields.get(key);
  }

  protected void addFiled(AbstractMap.SimpleEntry<Class<?>, String> key, Field field) {
    field.setAccessible(true);
    fields.put(key, field);
  }

  public String getMessageTemplate(Field field) {
    return messageTemplates.computeIfAbsent(field, data -> {
      Schema schema = field.getAnnotation(Schema.class);
      return (schema == null ? DEFAULT_MESSAGE_PREFIX : schema.description()) + DEFAULT_MESSAGE_VALUE_PLACEHOLDER
        + UniqueField.DEFAULT_MESSAGE_SUFFIX;
    });
  }
}
