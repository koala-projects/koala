package cn.koala.data.domain;

/**
 * 可排序的实体接口
 *
 * @author Houtaroy
 */
public interface Sortable {

  Long getSortIndex();

  void setSortIndex(Long sortIndex);
}
