package cn.koala.system.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @param <T> 主键类型
 * @author Houtaroy
 */
public interface Idable<T> {

  /**
   * 获取主键
   *
   * @return 主键
   */
  @Schema(description = "ID")
  T getId();

  /**
   * 设置主键
   *
   * @param id 主键
   */
  void setId(T id);
}
