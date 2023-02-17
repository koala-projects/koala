package cn.koala.system.entities;

import cn.koala.system.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 角色数据实体类
 *
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class RoleEntity extends BaseSystemEntity implements Role {
  protected String code;
  protected String name;
  protected String remark;
}
