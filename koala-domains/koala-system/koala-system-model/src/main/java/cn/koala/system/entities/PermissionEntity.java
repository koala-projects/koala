package cn.koala.system.entities;

import cn.koala.system.models.Permission;
import cn.koala.system.models.PermissionType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Houtaroy
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class PermissionEntity extends BaseEntity implements Permission {
  private PermissionType type;
  private String code;
  private String name;
  private String description;
  private PermissionEntity parent;
  private List<ApiEntity> apis;
}
