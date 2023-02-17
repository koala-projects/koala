package cn.koala.mybatis;

import java.util.Date;

/**
 * 审计模型
 *
 * @param <ID> 用户id类型
 * @author Houtaroy
 */
public interface AuditModel<ID> {
  /**
   * 获取创建用户ID
   *
   * @return 创建用户ID
   */
  ID getCreateUserId();

  /**
   * 设置创建用户ID
   *
   * @param id 创建用户ID
   */
  void setCreateUserId(ID id);

  /**
   * 获取创建时间
   *
   * @return 创建时间
   */
  Date getCreateTime();

  /**
   * 设置创建时间
   *
   * @param createTime 创建时间
   */
  void setCreateTime(Date createTime);

  /**
   * 获取最后更新用户ID
   *
   * @return 创建用户ID
   */
  ID getLastUpdateUserId();

  /**
   * 设置最后更新用户ID
   *
   * @param id 最后更新用户ID
   */
  void setLastUpdateUserId(ID id);

  /**
   * 获取最后更新时间
   *
   * @return 最后更新时间
   */
  Date getLastUpdateTime();

  /**
   * 设置最后更新时间
   *
   * @param lastUpdateTime 最后更新时间
   */
  void setLastUpdateTime(Date lastUpdateTime);

  /**
   * 获取删除用户ID
   *
   * @return 删除用户ID
   */
  ID getDeleteUserId();

  /**
   * 设置删除用户ID
   *
   * @param id 删除用户ID
   */
  void setDeleteUserId(ID id);

  /**
   * 获取删除时间
   *
   * @return 删除时间
   */
  Date getDeleteTime();

  /**
   * 设置删除时间
   *
   * @param deleteTime 删除时间
   */
  void setDeleteTime(Date deleteTime);
}
