package cn.koala.database;

import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 * 前缀数据库表过滤器
 *
 * @author Houtaroy
 */
@RequiredArgsConstructor
public class PrefixFilter implements Predicate<Table> {

  protected final String prefix;

  @Override
  public boolean test(Table table) {
    return table.getName().startsWith(prefix);
  }
}
