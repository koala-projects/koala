package cn.koala.persist.support;

import cn.koala.persist.AnnotationResolver;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 名称映射注解解析器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class NameMappingAnnotationResolver implements AnnotationResolver {

  private final Map<String, Class<? extends Annotation>> annotations;

  @Override
  public Class<? extends Annotation> resolveAnnotation(Method method) {
    return annotations.get(method.getName());
  }
}
