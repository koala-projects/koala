package cn.koala.system;

import cn.koala.persistence.Codeable;
import cn.koala.persistence.Deletable;
import cn.koala.persistence.Idable;
import cn.koala.persistence.Systemic;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Optional;

/**
 * 部门
 *
 * @author Houtaroy
 */
public interface Department extends Idable<String>, Codeable, Systemic, Deletable {
  /**
   * 获取上级部门
   *
   * @return 上级部门
   */
  Department getParent();

  /**
   * 获取上级部门ID, 如果不存在则返回null
   *
   * @return 上级部门ID
   */
  @JsonIgnore
  default String getParentId() {
    return Optional.ofNullable(getParent()).map(Idable::getId).orElse(null);
  }
}
