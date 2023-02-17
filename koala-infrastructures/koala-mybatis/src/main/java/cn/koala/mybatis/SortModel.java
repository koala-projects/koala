package cn.koala.mybatis;

/**
 * 排序模型
 *
 * @author Houtaroy
 */
public interface SortModel {
  /**
   * 获取排序索引
   *
   * @return 排序索引
   */
  Long getSortIndex();

  /**
   * 设置排序索引
   *
   * @param sortIndex 排序索引
   */
  void setSortIndex(Long sortIndex);
}
