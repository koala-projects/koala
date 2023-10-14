package cn.koala.persist.support;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 修改类描述
 *
 * @author Houtaroy
 */
public abstract class SpringBeanHelper {

  private static boolean isOrderly(Object bean) {
    if (bean == null) {
      return false;
    }
    if (bean instanceof Method method) {
      return isOrderly(method);
    } else {
      return isOrderly(bean.getClass());
    }
  }

  private static boolean isOrderly(Method method) {
    return AnnotationUtils.findAnnotation(method, Order.class) != null;
  }

  private static boolean isOrderly(Class<?> clazz) {
    return clazz.isAnnotationPresent(Order.class) || Ordered.class.isAssignableFrom(clazz);
  }

  public static <T> List<T> sort(List<T> beans) {
    List<T> result = new ArrayList<>(beans.size());
    List<T> orders = beans.stream().filter(SpringBeanHelper::isOrderly).toList();
    AnnotationAwareOrderComparator.sort(orders);
    result.addAll(orders);
    result.addAll(beans.stream().filter(bean -> !isOrderly(bean)).toList());
    return result;
  }
}
