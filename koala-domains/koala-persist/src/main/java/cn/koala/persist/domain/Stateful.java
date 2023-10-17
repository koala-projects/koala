package cn.koala.persist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 有状态的模型
 *
 * @author Houtaroy
 */
public interface Stateful {
  /**
   * 获取是否已启用
   *
   * @return 是否已启用
   */
  YesNo getIsEnabled();

  /**
   * 设置是否已启用
   *
   * @param isEnabled 是否启用
   */
  void setIsEnabled(YesNo isEnabled);

  @JsonIgnore
  default void setIsEnabledIfAbsent(YesNo isEnabled) {
    if (getIsEnabled() == null) {
      setIsEnabled(isEnabled);
    }
  }

  /**
   * 获取是否系统的
   *
   * @return 是否系统的
   */
  YesNo getIsSystemic();

  /**
   * 设置是否系统的
   *
   * @param isSystemic 是否系统的
   */
  void setIsSystemic(YesNo isSystemic);

  /**
   * 获取是否已删除的
   *
   * @return 否已删除的
   */
  YesNo getIsDeleted();

  /**
   * 设置否已删除的
   *
   * @param isDeleted 否已删除的
   */
  void setIsDeleted(YesNo isDeleted);
}
