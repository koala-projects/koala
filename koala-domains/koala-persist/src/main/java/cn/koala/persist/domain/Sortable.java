package cn.koala.persist.domain;

/**
 * 可排序的模型
 *
 * @author Houtaroy
 */
public interface Sortable {
  Long getSortIndex();

  void setSortIndex(Long sortIndex);
}
