package cn.koala.code.table;

import cn.koala.code.Generator;
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
public class TableMultiGenerator implements Generator<TableContext, Map<String, String>> {
  protected final List<TableGenerator> generators;

  @Override
  public Map<String, String> generate(TableContext source) {
    Map<String, String> result = new HashMap<>(generators.size());
    for (TableGenerator generator : generators) {
      result.put(generator.getTemplate(), generator.generate(source));
    }
    return result;
  }
}
