package cn.koala.util;

import cn.koala.exception.BusinessException;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 业务断言工具类
 *
 * @author Houtaroy
 */
public abstract class BusinessAssert {

  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new BusinessException(message);
    }
  }

  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new BusinessException(message);
    }
  }

  public static void notEmpty(Collection<?> collection, String message) {
    if (CollectionUtils.isEmpty(collection)) {
      throw new BusinessException(message);
    }
  }

  public static void hasLength(@Nullable String text, String message) {
    if (!StringUtils.hasLength(text)) {
      throw new BusinessException(message);
    }
  }
}
