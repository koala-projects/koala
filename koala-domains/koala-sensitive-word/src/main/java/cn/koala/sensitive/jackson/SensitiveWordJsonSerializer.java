package cn.koala.sensitive.jackson;

import cn.koala.sensitive.SensitiveWordFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

/**
 * Jackson敏感词过滤序列化器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SensitiveWordJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {
  protected final SensitiveWordFilter filter;
  protected final Character replacement;

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
    return new SensitiveWordJsonSerializer(filter, getReplacement(property));
  }

  protected char getReplacement(BeanProperty property) {
    return Optional.ofNullable(property.getAnnotation(Replacement.class)).map(Replacement::value).orElse('*');
  }

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(filter.filter(value, replacement));
  }
}
