package cn.koala.builder;

import cn.koala.jdbc.Table;
import cn.koala.persistence.Idable;

import java.util.List;

/**
 * 领域转换器
 *
 * @author Houtaroy
 */
public interface DomainConverter extends Idable<String> {
  /**
   * 将表转换为领域
   *
   * @param table 表
   * @return 领域
   */
  Object convert(Table table);

  /**
   * 获取领域参数列表, 用于展示
   *
   * @return 领域参数列表
   */
  List<DomainProperty> getProperties();
}
