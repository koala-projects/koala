package cn.koala.system.entities;

import cn.koala.system.Permission;
import cn.koala.system.PermissionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 权限数据实体类
 *
 * @author Houtaroy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class PermissionEntity extends BaseSystemEntity implements Permission {
  protected String code;
  protected String name;
  protected PermissionType type;
  protected String icon;
  protected String url;
  protected String component;
  protected String remark;
  protected Long parentId;
}
