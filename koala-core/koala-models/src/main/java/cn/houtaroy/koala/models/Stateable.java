package cn.houtaroy.koala.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author Houtaroy
 */
public interface Stateable {

  /**
   * 获取是否系统
   *
   * @return 是否系统
   */
  @Schema(description = "是否系统")
  YesNo getIsSystem();

  /**
   * 获取是否启用
   *
   * @return 是否启用
   */
  @Schema(description = "是否启用")
  YesNo getIsEnable();

  /**
   * 获取是否删除
   *
   * @return 是否删除
   */
  @Schema(description = "是否删除")
  YesNo getIsDelete();
}
