package cn.koala.code;

import cn.koala.code.database.Table;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库表代码生成器, 一次生成多个代码
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class DatabaseMultiGenerator implements Generator<Table, Map<String, String>> {
  protected final List<DatabaseGenerator> generators;

  @Override
  public Map<String, String> generate(Table source) throws Exception {
    Map<String, String> result = new HashMap<>(generators.size());
    for (DatabaseGenerator generator : generators) {
      result.put(generator.getTemplate(), generator.generate(source));
    }
    return result;
  }
}
