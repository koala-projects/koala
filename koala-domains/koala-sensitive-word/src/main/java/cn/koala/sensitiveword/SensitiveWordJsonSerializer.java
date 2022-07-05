package cn.koala.sensitiveword;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.Optional;

/**
 * 敏感词Jackson序列化器
 * <p>
 * 在对应的String类型属性上增加注解@JsonSerialize(using = SensitiveWordJsonSerializer.class)
 * <p>
 * 如需指定替换字符, 请使用注解{@link Replacement @Replacement}
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SensitiveWordJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {
  private final RefreshableSensitiveWordFilterChain filterChain;
  @Setter
  private char replacement = SensitiveWordFilter.DEFAULT_REPLACEMENT;

  @Override
  public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
    SensitiveWordJsonSerializer result = new SensitiveWordJsonSerializer(filterChain);
    result.setReplacement(getReplacement(property));
    return result;
  }

  @Override
  public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
    gen.writeString(filterChain.doFilter(value, replacement));
  }

  /**
   * 获取替换字符
   *
   * @param property 序列化属性
   * @return 替换字符
   */
  protected char getReplacement(BeanProperty property) {
    return Optional.ofNullable(property.getAnnotation(Replacement.class))
      .map(Replacement::value).orElse(SensitiveWordFilter.DEFAULT_REPLACEMENT);
  }
}
