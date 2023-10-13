package cn.koala.persist;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 方法实体监听器工厂
 *
 * @author Houtaroy
 */
public interface MethodEntityListenerFactory {

  List<Object> getEntityListeners(Method method, Object[] args);
}
