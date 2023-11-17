package cn.koala.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson工具类
 *
 * @author Houtaroy
 */
public abstract class JacksonUtils {

  private static final String EXCEPTION_JSON = "{ \"data\": \"JSON转换异常: %s\" }";

  public static String writeValueAsStringQuietly(ObjectMapper objectMapper, Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      return String.format(EXCEPTION_JSON, e.getLocalizedMessage());
    }
  }
}
