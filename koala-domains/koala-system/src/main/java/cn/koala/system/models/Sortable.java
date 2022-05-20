package cn.koala.system.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Houtaroy
 */
public interface Sortable {

  /**
   * 获取排序索引
   *
   * @return 排序索引
   */
  @Schema(description = "排序索引")
  Integer getSortIndex();
}
