package cn.houtaroy.koala.domain.entities;

import cn.houtaroy.koala.domain.models.Role;
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
public class RoleEntity extends BaseEntity implements Role {

  private String code;
  private String name;
  private String description;
  private List<PermissionEntity> permissions;
}
