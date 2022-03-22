package cn.houtaroy.koala.models;

import java.time.LocalDateTime;

/**
 * @author Houtaroy
 */
public interface Auditable {

  /**
   * 获取创建时间
   *
   * @return 创建时间
   */
  LocalDateTime getCreateTime();

  /**
   * 获取创建人
   *
   * @return 创建人实体
   */
  User getCreateUser();

  /**
   * 获取最后修改时间
   *
   * @return 最后修改时间
   */
  LocalDateTime getLastModifyTime();

  /**
   * 获取最后修改人
   *
   * @return 最后修改人实体
   */
  User getLastModifyUser();

  /**
   * 获取删除时间
   *
   * @return 删除时间
   */
  LocalDateTime getDeleteTime();

  /**
   * 获取删除人
   *
   * @return 最后删除人实体
   */
  User getDeleteUser();
}
