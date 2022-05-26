package cn.koala.web;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Houtaroy
 */
public final class PropertyHelper {
  private PropertyHelper() {
    throw new IllegalStateException("工具类不允许实例化");
  }

  /**
   * 获取对象值为null的属性名称
   *
   * @param source 源对象
   * @return 属性定义集合
   */
  public static Set<PropertyDescriptor> getNullProperty(Object source) {
    BeanWrapper wrapper = new BeanWrapperImpl(source);
    return Arrays.stream(wrapper.getPropertyDescriptors())
      .filter(propertyDescriptor -> wrapper.getPropertyValue(propertyDescriptor.getName()) == null)
      .collect(Collectors.toSet());
  }
}
