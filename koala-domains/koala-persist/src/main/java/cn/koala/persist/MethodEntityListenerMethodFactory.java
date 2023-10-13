package cn.koala.persist;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法实体监听器方法工厂
 *
 * @author Houtaroy
 */
public interface MethodEntityListenerMethodFactory {

  List<Method> getEntityListenerMethods(Object listener, Method method);
}
