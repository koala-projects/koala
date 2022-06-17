package cn.koala.utils;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 指定单词复数转换器
 * 指定单词的复数形式, 从文本中读取
 * 例如: 文本内容为"person,people", 则将单词person转换为复数时结果为people
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class SpecifyPluralConverter implements PluralConverter {
  protected final Map<String, String> specifyPlurals;

  /**
   * 构造函数
   *
   * @param filename 资源文件名
   */
  public SpecifyPluralConverter(String filename) {
    specifyPlurals = new HashMap<>();
    ResourceUtil.readLines(filename).ifPresent(lines -> lines.forEach(line -> {
      String[] parts = line.split(",");
      specifyPlurals.put(parts[0], parts[1]);
    }));
  }

  @Override
  public boolean isSupported(String singular) {
    return specifyPlurals.containsKey(singular);
  }

  @Override
  public String convert(String singular) {
    return specifyPlurals.get(singular);
  }
}
