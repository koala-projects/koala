package cn.koala.util;

import cn.koala.exception.BusinessException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 业务断言工具类
 *
 * @author Houtaroy
 */
public abstract class Assert extends org.springframework.util.Assert {

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

  public static void notEmpty(Collection<?> collection, @NonNull String message) {
    if (CollectionUtils.isEmpty(collection)) {
      throw new BusinessException(message);
    }
  }

  public static void hasLength(@Nullable String text, @NonNull String message) {
    if (!StringUtils.hasLength(text)) {
      throw new BusinessException(message);
    }
  }
}
