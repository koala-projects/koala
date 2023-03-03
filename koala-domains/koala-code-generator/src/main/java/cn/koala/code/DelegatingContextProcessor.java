package cn.koala.code;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 委派上下文加工器
 *
 * @author Houtaroy
 */
@Slf4j
@RequiredArgsConstructor
public class DelegatingContextProcessor implements ContextProcessor {
  protected final List<ContextProcessor> processors;

  @Override
  public Map<String, Object> process(Object context) {
    Map<String, Object> result = new HashMap<>();
    for (ContextProcessor processor : processors) {
      doProcess(result, processor, context);
    }
    return result;
  }

  protected void doProcess(Map<String, Object> context, ContextProcessor processor, Object object) {
    Map<String, Object> temp = processor.process(object);
    temp.forEach((key, value) -> {
      if (context.containsKey(key)) {
        LOGGER.warn("上下文加工器[name={}]覆盖了属性[{}]", processor.getName(), key);
      }
      context.put(key, value);
    });
  }
}
