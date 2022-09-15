package cn.koala.database;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;

/**
 * 前缀数据库表过滤器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class PrefixTableFilter implements Predicate<Table> {

  protected final String prefix;

  @Override
  public boolean test(Table table) {
    return StringUtils.isBlank(prefix) || table.getName().startsWith(prefix);
  }
}
